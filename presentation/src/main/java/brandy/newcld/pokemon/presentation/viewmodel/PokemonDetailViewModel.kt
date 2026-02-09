package brandy.newcld.pokemon.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import brandy.newcld.pokemon.domain.usecase.GetPokemonInfoUseCase
import brandy.newcld.pokemon.presentation.model.PokemonAppBarInfoModel
import brandy.newcld.pokemon.presentation.model.PokemonInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonInfoUseCase,
): BaseViewModel() {

//    fun getPokemonInfo(pid: Int? = 1): PokemonInfoModel {
//        viewModelScope.launch {
//            getPokemonDetailUseCase(pid!!)
//        }
//    }
//
//    fun getAppBarInfo(pid: Int? = 1): PokemonAppBarInfoModel {
//
//    }

}