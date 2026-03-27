package brandy.newcld.pokemon.presentation.util

import android.graphics.Color

object ColorUtil {
    /**
     * 라이트 모드일 때 사용되는 색상
     */
    fun toPastelColor(color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)

        // ⭐ 채도 최소값 보장
        hsv[1] = (hsv[1] * 0.5f).coerceIn(0.15f, 1f)
        hsv[2] = (hsv[2] * 1.15f).coerceIn(0f, 0.95f)

        return Color.HSVToColor(hsv)
    }

    /**
     * 다크 모드일 때 사용되는 색상
     */
    fun toDarkerColor(color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)

        // 채도는 살짝 유지 (너무 쨍하면 줄이기)
        hsv[1] = (hsv[1] * 0.9f).coerceIn(0.2f, 0.85f)

        // 밝기 낮추기 (중요)
        hsv[2] = (hsv[2] * 0.6f).coerceIn(0.2f, 0.75f)

        return Color.HSVToColor(hsv)
    }
}