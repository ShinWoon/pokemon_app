package brandy.newcld.pokemon.ui.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
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
    onClickBack: () -> Unit,
) {
    val slideSpec = tween<IntOffset>(durationMillis = 300)

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = slideSpec,
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = slideSpec,
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = slideSpec,
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = slideSpec,
            )
        },
    ) {
        composable(route = "pokemon_list") {
            PokemonListScreen(
                pokemonListViewModel = hiltViewModel(),
                onItemClick = { pid -> navController.navigate("pokemon_detail/${pid}") },
            )
        }
        composable(
            route = "pokemon_detail/{pid}",
            arguments = listOf(navArgument("pid") { type = NavType.IntType }),
        ) { backStackEntry ->
            val pid = backStackEntry.arguments?.getInt("pid")

            PokemonDetailScreen(
                pid = pid,
                pokemonDetailViewModel = hiltViewModel(),
                onClickBack = onClickBack
            )
        }
    }
}
