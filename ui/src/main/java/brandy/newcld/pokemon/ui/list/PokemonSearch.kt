package brandy.newcld.pokemon.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.presentation.model.SearchResultItem
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.Background
import brandy.newcld.pokemon.ui.theme.DarkModeBackground
import brandy.newcld.pokemon.ui.theme.DarkModeDivider
import brandy.newcld.pokemon.ui.theme.LightGray
import brandy.newcld.pokemon.ui.theme.Secondary
import brandy.newcld.pokemon.ui.theme.Typography
import brandy.newcld.pokemon.ui.theme.hintOf
import brandy.newcld.pokemon.ui.theme.primaryTextOf
import brandy.newcld.pokemon.ui.theme.secondaryTextOf
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClose: () -> Unit,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val textColor = primaryTextOf(isDarkMode)
    val hintColor = hintOf(isDarkMode)
    val backgroundColor = if (isDarkMode) DarkModeBackground else Background

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (query.isEmpty()) {
                    Text(
                        text = "포켓몬 이름 또는 번호",
                        style = Typography.titleMedium,
                        color = hintColor,
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    singleLine = true,
                    textStyle = Typography.titleMedium.copy(color = textColor),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back_round),
                    contentDescription = "닫기",
                    tint = textColor,
                )
            }
        },
        actions = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        painter = painterResource(R.drawable.close_round),
                        contentDescription = "지우기",
                        tint = textColor,
                    )
                }
            }
        },
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListTopBar(
    onSearchClick: () -> Unit,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
) {
    val textColor = if (isDarkMode) Background else Secondary
    val backgroundColor = if (isDarkMode) DarkModeBackground else Background

    TopAppBar(
        title = {
            Text(
                text = "포켓몬 도감",
                style = Typography.titleLarge,
                color = textColor,
            )
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(R.drawable.search_round),
                    contentDescription = "검색",
                    tint = textColor,
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
    )
}

@Composable
fun SearchResultDropdown(
    query: String,
    results: List<SearchResultItem>,
    onItemClick: (Int) -> Unit,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isDarkMode) DarkModeBackground else Background
    val dividerColor = if (isDarkMode) DarkModeDivider else LightGray

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor),
    ) {
        when {
            query.isBlank() -> Unit
            results.isEmpty() -> EmptyResult(isDarkMode = isDarkMode)
            else -> LazyColumn(
                contentPadding = PaddingValues(top = 4.dp),
            ) {
                items(items = results, key = { it.id }) { item ->
                    SearchResultRow(
                        item = item,
                        isDarkMode = isDarkMode,
                        onClick = { onItemClick(item.id) },
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(color = dividerColor),
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchResultRow(
    item: SearchResultItem,
    isDarkMode: Boolean,
    onClick: () -> Unit,
) {
    val textColor = primaryTextOf(isDarkMode)
    val idColor = secondaryTextOf(isDarkMode)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(40.dp)
                .background(color = LightGray.copy(alpha = 0.2f), shape = CircleShape),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item.koName,
            style = Typography.titleMedium,
            color = textColor,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "#${item.id}",
            style = Typography.bodyMedium,
            color = idColor,
        )
    }
}

@Composable
private fun EmptyResult(isDarkMode: Boolean) {
    val textColor = secondaryTextOf(isDarkMode)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "검색 결과가 없습니다",
            style = Typography.bodyMedium,
            color = textColor,
        )
    }
}
