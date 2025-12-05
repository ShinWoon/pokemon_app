package brandy.newcld.pokemon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokemonApplication: Application() {
    val baseUrl: String
        get() = "https://pokeapi.co/api/v2/"

    override fun onCreate() {
        super.onCreate()
    }

}