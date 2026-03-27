package brandy.newcld.pokemon.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.palette.graphics.Palette
import brandy.newcld.pokemon.ui.theme.DefaultLightGray
import coil3.Bitmap
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PaletteUtil {
    suspend fun paletteBackgroundColor(
        imageUrl: String,
        context: Context
    ): Int = withContext(Dispatchers.IO){
        val default = DefaultLightGray.toArgb()

        // Coil로 비트맵을 직접 불러와 Palette 돌린다
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false)
            .build()

        // 백그라운드 스레드에서 실행
        val result = loader.execute(request)

        if(result !is SuccessResult) return@withContext default

        val image = result.image
        val bitmap: Bitmap = image.toBitmap()
        withContext(Dispatchers.Default) {
            val palette = Palette.from(bitmap).generate()
            palette.getMutedColor(default)
        }
    }
}