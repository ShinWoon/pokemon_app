package brandy.newcld.pokemon.injection

import android.app.Application
import brandy.newcld.pokemon.PokemonApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(application: Application): String =
        (application as PokemonApplication).baseUrl
}