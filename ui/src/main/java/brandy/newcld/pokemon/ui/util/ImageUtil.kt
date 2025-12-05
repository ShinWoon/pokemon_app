package brandy.newcld.pokemon.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import brandy.newcld.pokemon.ui.theme.DefaultLightGray
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
        imageUrl: String,
        modifier: Modifier = Modifier,
        showText: Boolean = false
    ) {
        val context = LocalContext.current
        var backgroundColor by remember { mutableStateOf(DefaultLightGray) }
        Box(
            modifier = modifier
                .size(160.dp)
                .background(backgroundColor)
                .padding(16.dp),
        ) {
            // 이미지 렌더링 (색 추출 X)
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.size(140.dp)
            )

            if(showText) {
                Column (
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = Color.DarkGray.copy(alpha = 0.6f))
                ) {
                    Text(text = "한국어 이름")
                    Spacer(modifier = modifier.size(8.dp))
                    Text(text = "english name")
                }
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
                    val dominantInt = palette?.getLightVibrantColor(default) ?: default
                    backgroundColor = Color(dominantInt)
                }
            }
        }
    }
}