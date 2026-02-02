package brandy.newcld.pokemon.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
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

                var collapsedFraction by remember { mutableFloatStateOf(0f) }
                var expandedTitleCoords by remember { mutableStateOf<LayoutCoordinates?>(null) }
                var collapsedTitleCoords by remember { mutableStateOf<LayoutCoordinates?>(null) }

                Scaffold(
                    topBar = { MainAppBar(currentRoute = currentRoute, onClickBack = {navController.popBackStack()}, onCoords = { collapsedTitleCoords = it}) }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        AppNavHost(
                            navController = navController,
                            startDestination = startDestination,
                            onDetailHeaderAnchor = { expandedTitleCoords = it },
                            onDetailFraction = { collapsedFraction = it }
                        )
                    }
                }
            }
        }
    }
}