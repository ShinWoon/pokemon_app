package brandy.newcld.pokemon.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import brandy.newcld.pokemon.ui.list.PokemonListScreen

@Composable
fun AppNavHost(
    innerPadding: PaddingValues,
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController = navController,
        startDestination = startDestination,
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