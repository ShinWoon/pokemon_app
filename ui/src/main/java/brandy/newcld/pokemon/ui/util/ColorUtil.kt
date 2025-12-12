package brandy.newcld.pokemon.ui.util

import android.graphics.Color

object ColorUtil {

    fun toPastelColor(color: Int): Int {
        // RGB -> HSV
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)

        hsv[1] = (hsv[1] * 0.5f).coerceIn(0f, 1f)   // 채도 낮추기
        hsv[2] = (hsv[2] * 1.3f).coerceIn(0f, 1f)   // 밝기 올리기

        return Color.HSVToColor(hsv)
    }
}