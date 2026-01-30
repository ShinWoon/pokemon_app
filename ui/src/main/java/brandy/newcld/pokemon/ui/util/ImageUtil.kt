package brandy.newcld.pokemon.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import brandy.newcld.pokemon.ui.theme.DefaultLightGray
import brandy.newcld.pokemon.ui.util.ColorUtil.toPastelColor
import coil3.Bitmap
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ImageUtil {
    @Composable
    fun PaletteBackgroundWithImage(
        modifier: Modifier = Modifier,
        imageUrl: String,
        content: @Composable () -> Unit = {},
        boxModifier: Modifier,
        boxShape: RoundedCornerShape,
        imageModifier: Modifier,
        order: String
    ) {
        val context = LocalContext.current
        var backgroundColor by remember { mutableStateOf(DefaultLightGray) }
        Box(
            modifier = boxModifier.background(color = backgroundColor, shape = boxShape)
        ) {
            if(order.lowercase() == "column") {
                OrderByColumn(
                    content =  {
                        // 이미지 렌더링 (색 추출 X)
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(imageUrl)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = imageModifier
                        )
                        content()
                    }
                )
            } else {
                OrderByRow(
                    content = {
                        // 이미지 렌더링 (색 추출 X)
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(imageUrl)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = imageModifier,
                            alignment = Alignment.BottomCenter
                        )
                        content()
                    }
                )
            }

        }

        // Coil로 비트맵을 직접 불러와 Palette 돌린다
        LaunchedEffect(imageUrl) {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false)
                .build()

            // 백그라운드 스레드에서 실행
            val result = withContext(Dispatchers.IO) {
                loader.execute(request)
            }

            if (result is SuccessResult) {
                val image = result.image
                val bitmap: Bitmap = image.toBitmap()

                Palette.from(bitmap).generate { palette ->
                    val default = DefaultLightGray.toArgb()
                    val mutedInt = palette?.getMutedColor(default) ?: default
                    val pastelColor = toPastelColor(mutedInt)
                    backgroundColor = Color(pastelColor)
                }
            }
        }
    }

    @Composable
    private fun OrderByRow(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            content()
        }
    }

    @Composable
    private fun OrderByColumn(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}