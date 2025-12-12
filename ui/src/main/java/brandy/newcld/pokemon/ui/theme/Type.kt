package brandy.newcld.pokemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import brandy.newcld.pokemon.ui.R

val suit = FontFamily(
    Font(R.font.suit_extra_bold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.suit_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.suit_semi_bold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.suit_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.suit_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.suit_thin, FontWeight.Thin, FontStyle.Normal),
    Font(R.font.suit_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.suit_extra_light, FontWeight.ExtraLight, FontStyle.Normal),
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = suit,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
)