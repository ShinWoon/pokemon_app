package brandy.newcld.pokemon.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.Background
import brandy.newcld.pokemon.ui.theme.Secondary
import brandy.newcld.pokemon.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    currentRoute: String? = "",
    onClickBack: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = "포켓몬 도감",
                style = Typography.titleLarge,
                color = Secondary
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        navigationIcon = {
            if(currentRoute != "pokemon_list") {
                IconButton(
                    onClick = { onClickBack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back_round),
                        contentDescription = "back button icon",
                        modifier = Modifier.size(16.dp),
                        tint = Secondary
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Background
        )
    )
}
