package brandy.newcld.pokemon.ui.nav

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
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
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = "pokemon_list",
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300, easing = LinearEasing)
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            PokemonListScreen(
                pokemonListViewModel = hiltViewModel(),
                onItemClick = { pid -> navController.navigate("pokemon_detail/${pid}") },

            )
        }
        composable(
            route = "pokemon_detail/{pid}",
            arguments = listOf(navArgument("pid"){ type = NavType.IntType }),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(300, easing = LinearEasing)
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
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