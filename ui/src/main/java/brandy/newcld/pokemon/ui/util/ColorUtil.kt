package brandy.newcld.pokemon.ui.util

import android.graphics.Color

object ColorUtil {

    fun toPastelColor(color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)

        // ⭐ 채도 최소값 보장
        hsv[1] = (hsv[1] * 0.5f).coerceIn(0.15f, 1f)
        hsv[2] = (hsv[2] * 1.15f).coerceIn(0f, 0.95f)

        return Color.HSVToColor(hsv)
    }
}