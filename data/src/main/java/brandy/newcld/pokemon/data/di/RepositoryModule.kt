package brandy.newcld.pokemon.data.di

import brandy.newcld.pokemon.data.repositoryImpl.PokemonRepositoryImpl
import brandy.newcld.pokemon.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository
}