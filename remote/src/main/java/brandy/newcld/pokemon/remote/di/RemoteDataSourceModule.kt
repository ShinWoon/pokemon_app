package brandy.newcld.pokemon.remote.di

import brandy.newcld.pokemon.data.remote.RemoteDataSource
import brandy.newcld.pokemon.remote.datasourceImpl.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSource: RemoteDataSourceImpl
    ): RemoteDataSource

}