package brandy.newcld.pokemon.remote.di

import brandy.newcld.pokemon.remote.api.ApiService
import brandy.newcld.pokemon.remote.api.createApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(
        @Named("baseUrl") baseUrl: String,
    ): ApiService = createApiService(baseUrl)
}