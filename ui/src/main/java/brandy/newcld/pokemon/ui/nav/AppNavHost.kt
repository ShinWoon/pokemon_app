package brandy.newcld.pokemon.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import brandy.newcld.pokemon.ui.list.PokemonListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "pokemon_list",
    ) {
        composable("pokemon_list") {
            PokemonListScreen(
                pokemonListViewModel = hiltViewModel()
            )
        }
        composable("pokemon_detail") {

        }
    }
}