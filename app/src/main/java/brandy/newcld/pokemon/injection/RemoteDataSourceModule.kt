package brandy.newcld.pokemon.injection

import brandy.newcld.pokemon.data.remote.PokemonRemoteDataSource
import brandy.newcld.pokemon.remote.datasourceImpl.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSource: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource

}