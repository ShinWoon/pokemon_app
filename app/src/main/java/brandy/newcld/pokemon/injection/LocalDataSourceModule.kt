package brandy.newcld.pokemon.injection

import brandy.newcld.pokemon.data.local.PokemonLocalDataSource
import brandy.newcld.pokemon.local.impl.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(source: LocalDataSourceImpl): PokemonLocalDataSource
}