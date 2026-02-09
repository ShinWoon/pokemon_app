package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import brandy.newcld.pokemon.presentation.viewmodel.PokemonDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    pokemonDetailViewModel: PokemonDetailViewModel,
    pid: Int? = 1,
    onClickBack: () -> Unit,
) {
    val COLLAPSED_TOP_BAR_HEIGHT = 64.dp
    val EXPANDED_TOP_BAR_HEIGHT = 256.dp

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOP_BAR_HEIGHT.toPx() - COLLAPSED_TOP_BAR_HEIGHT.toPx()
    }

    val listState = rememberLazyListState()
    val isCollapsed by remember {
        derivedStateOf {
            val isFirstItemHidden = listState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || listState.firstVisibleItemIndex > 0
        }
    }

    LaunchedEffect(Unit) {
//        pokemonDetailViewModel.getPokemonInfo(pid = pid)
    }

    Box {
        CollapsedAppBar(
            modifier = modifier.zIndex(2f),
            boxModifier = modifier
                .zIndex(2f)
                .fillMaxWidth()
                .height(COLLAPSED_TOP_BAR_HEIGHT)
                .background(color = Color.White),
            onClickBack = onClickBack,
            isCollapsed = isCollapsed,
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pid}.png",
            typeImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/18.png",
            name = "이름"
        )
        LazyColumn(
            modifier = modifier.padding(top = COLLAPSED_TOP_BAR_HEIGHT),
            state = listState
        ) {
            item {
                ExpandedAppBar(
                    boxModifier = modifier
                        .fillMaxWidth()
                        .height(EXPANDED_TOP_BAR_HEIGHT - COLLAPSED_TOP_BAR_HEIGHT)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                        ),
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pid}.png",
                    koName = "이름",
                    engName = "name"
                )
            }
            item {
                Spacer(Modifier.height(16.dp))
                Text("상세 내용 id=$pid", Modifier.padding(horizontal = 16.dp))
                Spacer(Modifier.height(1200.dp)) // 테스트용
            }
        }
    }
}