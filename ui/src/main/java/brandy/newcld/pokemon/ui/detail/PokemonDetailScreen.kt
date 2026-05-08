package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import brandy.newcld.pokemon.presentation.viewmodel.PokemonDetailViewModel
import brandy.newcld.pokemon.ui.state.ErrorScreen
import brandy.newcld.pokemon.ui.state.LoadingScreen
import brandy.newcld.pokemon.ui.theme.DarkGray
import brandy.newcld.pokemon.ui.theme.Hint
import brandy.newcld.pokemon.ui.theme.LightText

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
    val isDarkMode = isSystemInDarkTheme()

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
    val evolutionChainInfo by pokemonDetailViewModel.evolutionChainUiState.collectAsState()

    LaunchedEffect(Unit) {
        pokemonDetailViewModel.getPokemonInfo(pid = pid)
        pokemonDetailViewModel.loadLocalInfo(pid = pid)
    }

    val error = localInfo.error ?: remoteInfo.error ?: descriptionInfo.error ?: evolutionChainInfo.error
    if (error != null) {
        ErrorScreen(
            error = error,
            isDarkMode = isDarkMode,
            onRetry = {
                pokemonDetailViewModel.getPokemonInfo(pid = pid)
                pokemonDetailViewModel.loadLocalInfo(pid = pid)
            },
        )
        return
    }

    if (localInfo.isLoading || remoteInfo.isLoading || descriptionInfo.isLoading || evolutionChainInfo.isLoading) {
        LoadingScreen(isDarkMode = isDarkMode)
        return
    }

    val local = localInfo.data ?: return
    val remote = remoteInfo.data ?: return

    val bgColor = if (isDarkMode) Color(local.nightTimeColor) else Color(local.dayTimeColor)
    val isLightBg = bgColor.luminance() > 0.5f
    val onBgColor = if (isLightBg) DarkGray else LightText
    val onBgSubColor = if (isLightBg) Hint else LightText.copy(alpha = 0.7f)

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
            onBgColor = onBgColor,
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
                    koNameColor = onBgColor,
                    engNameColor = onBgSubColor,
                )
            }
            item {
                PokemonDetailContent(
                    modifier = modifier,
                    isDarkMode = isDarkMode,
                    remoteInfo = remoteInfo,
                    descriptionInfo = descriptionInfo,
                    evolutionChainInfo = evolutionChainInfo,
                    onPlayCry = pokemonDetailViewModel::playCry,
                )
            }
        }
    }
}