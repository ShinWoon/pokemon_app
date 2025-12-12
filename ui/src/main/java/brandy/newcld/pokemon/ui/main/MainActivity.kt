package brandy.newcld.pokemon.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import brandy.newcld.pokemon.ui.nav.AppNavHost
import brandy.newcld.pokemon.ui.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonAppTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val startDestination = "pokemon_list"
                Scaffold(
                    topBar = { MainAppBar(currentRoute = currentRoute, onClickBack = {navController.popBackStack()}) }
                ) { innerPadding ->
                    AppNavHost(
                        innerPadding = innerPadding,
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}