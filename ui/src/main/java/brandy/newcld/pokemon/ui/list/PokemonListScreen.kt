package brandy.newcld.pokemon.ui.list

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.theme.DefaultLightGray

import coil3.asDrawable
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.allowHardware
import androidx.core.graphics.createBitmap
import androidx.palette.graphics.Palette

@Composable
fun PokemonListScreen() {
    Box {
        PokemonListItem()
    }
}

@Composable
fun PokemonListItem() {
    val context = LocalContext.current
    var backgroundColor by remember { mutableStateOf(DefaultLightGray) }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png")
            .allowHardware(false)
            .build()
    )

    val state = painter.state

    LaunchedEffect(state) {
        if(state is AsyncImagePainter.State.Success) {
            // coil image -> drawable
            val drawable = state.result.image.asDrawable(context.resources)

            // drawable -> bitmap
            val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: drawableToBitmap(drawable)

            // palette 추출
            Palette.from(bitmap).generate { p ->
                val vibrant = p?.getVibrantColor(DefaultLightGray.toArgb()) ?: DefaultLightGray.toArgb()
                backgroundColor = Color(vibrant)
            }
        }
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
    ) {
        Image(painter, contentDescription = "image", modifier = Modifier.requiredSize(40.dp))
        Text(
            text = ""
        )
    }
}


private fun drawableToBitmap(drawable: Drawable): Bitmap {
    // BitmapDrawable이 아니어도 안전하게 변환
    val width = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 1
    val height = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 1
    val bmp = createBitmap(width, height)
    val canvas = Canvas(bmp)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bmp
}

@Preview
@Composable
fun PokemonListScreenPreview() {
    PokemonListScreen()
}
