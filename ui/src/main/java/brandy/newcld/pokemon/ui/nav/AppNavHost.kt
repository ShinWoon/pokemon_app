package brandy.newcld.pokemon.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import brandy.newcld.pokemon.ui.detail.PokemonDetailScreen
import brandy.newcld.pokemon.ui.list.PokemonListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    onDetailHeaderAnchor: (LayoutCoordinates) -> Unit,
    onDetailFraction: (Float) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("pokemon_list") {
            PokemonListScreen(
                pokemonListViewModel = hiltViewModel(),
                onItemClick = { pid -> navController.navigate("pokemon_detail/${pid}") }
            )
        }
        composable(
            route = "pokemon_detail/{pid}",
            arguments = listOf(navArgument("pid"){ type = NavType.IntType })
        ) { backStackEntry ->
            val pid = backStackEntry.arguments?.getInt("pid")

            PokemonDetailScreen(
                onCollapsedFraction = onDetailFraction,
                onExpandedTitleCoords = onDetailHeaderAnchor,
                pid = pid
            )
        }
    }
}