package brandy.newcld.pokemon.injection

import brandy.newcld.pokemon.presentation.util.SoundPlayer
import brandy.newcld.pokemon.ui.util.AppSoundPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SoundModule {

    @Provides
    @ViewModelScoped
    fun provideSoundPlayer(): SoundPlayer = AppSoundPlayer()
}
