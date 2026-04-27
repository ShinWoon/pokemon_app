package brandy.newcld.pokemon.ui.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean = false,
    descriptionText: String
) {
    DetailCommonCard (
        modifier = modifier,
        isDarkMode = isDarkMode
    ) {
        Text(text = descriptionText, modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp))
    }
}