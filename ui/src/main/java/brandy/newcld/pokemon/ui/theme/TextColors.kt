package brandy.newcld.pokemon.ui.theme

import androidx.compose.ui.graphics.Color

fun primaryTextOf(isDarkMode: Boolean): Color =
    if (isDarkMode) LightText else PrimaryText

fun secondaryTextOf(isDarkMode: Boolean): Color =
    if (isDarkMode) DarkModeSecondaryText else SecondaryText

fun hintOf(isDarkMode: Boolean): Color =
    if (isDarkMode) DarkModeHint else Hint