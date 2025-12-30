package brandy.newcld.pokemon.ui.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import brandy.newcld.pokemon.ui.theme.Background

@Composable
fun PokemonDataLoadScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

    }
}