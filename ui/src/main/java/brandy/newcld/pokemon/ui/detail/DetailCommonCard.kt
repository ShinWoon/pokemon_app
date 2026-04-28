package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.theme.DarkModeCardBackground
import brandy.newcld.pokemon.ui.theme.LightModeCardBackground

@Composable
fun DetailCommonCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    title: String = "",
    content: @Composable () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if(isDarkMode) DarkModeCardBackground else LightModeCardBackground
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 12.dp, bottom = 8.dp)
    ) {
        Column {
            if(title != "") {
                Text(text = title, modifier = modifier.padding(start = 16.dp, top = 12.dp, bottom = 6.dp))
            }
            content()
        }
    }
}

@Composable
fun VerticalDivider(modifier: Modifier = Modifier) {
    Box(modifier = modifier.width(1.dp).height(40.dp).background(Color(0xFFE0E0E0)))
}