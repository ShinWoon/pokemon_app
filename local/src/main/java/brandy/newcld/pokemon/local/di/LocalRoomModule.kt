package brandy.newcld.pokemon.local.di

import android.content.Context
import androidx.room.Room
import brandy.newcld.pokemon.local.room.AppDatabase
import brandy.newcld.pokemon.local.room.RoomConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalRoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class,
            name = RoomConstant.ROOM_DB_NAME
        )
}