package brandy.newcld.pokemon.ui.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import brandy.newcld.pokemon.ui.R
import brandy.newcld.pokemon.ui.theme.Primary
import brandy.newcld.pokemon.ui.theme.Secondary
import brandy.newcld.pokemon.ui.util.ImageUtil.PaletteBackground
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@Composable
fun CollapsingAppBar (
    modifier: Modifier = Modifier,
    boxModifier: Modifier,
    pid: Int? = 1,
    progress: Float,
    onClickBack: () -> Unit,
) {
    Box(
        modifier = boxModifier
    ) {
        CollapsingAppBarLayout(
            progress = progress,
            modifier = modifier,
            background = {
                // 이미지랑 이름 텍스트
                PaletteBackground(
                    modifier = modifier,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pid}.png",
                    boxModifier = modifier.fillMaxSize(),
                    boxShape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp),
                    content = {}
                )
            },
            pokemonImg = {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pid}.png",)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(200.dp)
                        .padding(16.dp),
                    alignment = Alignment.BottomCenter
                )
            },
            pokemonName = {
                Text("영어 이름")
            },
            backBtn = {
                // 뒤로가기 버튼
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
            },
            appbarImg = {
                // 앱바 아이콘
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pid}.png",
                    contentDescription = null,
                    modifier = modifier.size(24.dp)
                )
            },
            typeImg = {
                // 속성 아이콘
                AsyncImage(
                    model = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-viii/sword-shield/18.png",
                    contentDescription = null,
                    modifier = modifier.size(16.dp)
                )
            }
        )
    }
}

@Composable
private fun CollapsingAppBarLayout(
    modifier: Modifier = Modifier,
    progress: Float,
    background: @Composable () -> Unit,
    pokemonImg: @Composable () -> Unit,
    pokemonName: @Composable () -> Unit,
    backBtn: @Composable () -> Unit,
    appbarImg: @Composable () -> Unit,
    typeImg: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier,
        content = {
            Box(modifier.layoutId("background")) { background() }
            Box(modifier.layoutId("pokemon_img")) { pokemonImg() }
            Box(modifier.layoutId("pokemon_name")) { pokemonName() }
            Box(modifier.layoutId("appbar_back_button")) { backBtn()}
            Box(modifier.layoutId("appbar_pokemon_img")) { appbarImg() }
            Box(modifier.layoutId("pokemon_type_img")) { typeImg() }
        }
    ) { measurables, constraints ->

        Log.d("TAG", "CollapsingAppBarLayout: ${measurables.size}")

        val bg = measurables.first { it.layoutId == "background" }.measure(constraints)
        val pokeImg = measurables.first { it.layoutId == "pokemon_img" }.measure(constraints)
        val pokeName = measurables.first { it.layoutId == "pokemon_name" }.measure(constraints)
        val backBtn = measurables.first { it.layoutId == "appbar_back_button" }.measure(constraints)
        val appbarImg = measurables.first { it.layoutId == "appbar_pokemon_img" }.measure(constraints)
        val typeImg = measurables.first { it.layoutId == "pokemon_type_img" }.measure(constraints)

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            bg.placeRelative(0,0)
        }
    }
}