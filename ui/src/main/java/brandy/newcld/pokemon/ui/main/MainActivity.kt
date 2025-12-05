package brandy.newcld.pokemon.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import brandy.newcld.pokemon.ui.list.PokemonListScreen
import brandy.newcld.pokemon.ui.nav.AppNavHost
import brandy.newcld.pokemon.ui.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                AppNavHost()
            }
        }
    }
}