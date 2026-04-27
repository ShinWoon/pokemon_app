package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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

    val localInfo by pokemonDetailViewModel.localAppBarInfoUiState.collectAsState()
    val remoteInfo by pokemonDetailViewModel.remotePokemonInfoUiState.collectAsState()
    val descriptionInfo by pokemonDetailViewModel.descriptionUiState.collectAsState()

    LaunchedEffect(Unit) {
        pokemonDetailViewModel.getPokemonInfo(pid = pid)
        pokemonDetailViewModel.loadLocalInfo(pid = pid)
    }

    val error = localInfo.error ?: remoteInfo.error ?: descriptionInfo.error
    if (error != null) {
        ErrorView(
            message = error.message ?: "데이터를 불러오지 못했어요",
            onRetry = {
                pokemonDetailViewModel.getPokemonInfo(pid = pid)
                pokemonDetailViewModel.loadLocalInfo(pid = pid)
            },
        )
        return
    }

    if (localInfo.isLoading || remoteInfo.isLoading || descriptionInfo.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val local = localInfo.data ?: return
    val remote = remoteInfo.data ?: return

    val isDarkMode = isSystemInDarkTheme()
    val bgColor = if (isDarkMode) Color(local.nightTimeColor) else Color(local.dayTimeColor)

    Box(modifier = Modifier.fillMaxSize()) {
        CollapsedAppBar(
            modifier = modifier.zIndex(2f),
            boxModifier = modifier
                .zIndex(2f)
                .fillMaxWidth()
                .height(COLLAPSED_TOP_BAR_HEIGHT)
                .background(color = bgColor),
            onClickBack = onClickBack,
            isCollapsed = isCollapsed,
            imageUrl = remote.appBarIconUrl,
            typeImageUrl = remote.typeImgUrl,
            name = local.koName,
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
                            color = bgColor,
                            shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                        ),
                    imageUrl = remote.imgUrl,
                    koName = local.koName,
                    engName = local.engName,
                )
            }
            item {
                PokemonDetailContent(
                    modifier = modifier,
                    isDarkMode = isDarkMode,
                    remoteInfo = remoteInfo,
                    descriptionInfo = descriptionInfo,
                )
            }
        }
    }
}

@Composable
private fun ErrorView(
    message: String,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = message)
        Button(onClick = onRetry, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "다시 시도")
        }
    }
}