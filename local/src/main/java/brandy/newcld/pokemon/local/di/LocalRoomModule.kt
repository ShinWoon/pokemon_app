package brandy.newcld.pokemon.local.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import brandy.newcld.pokemon.local.room.AbilityDao
import brandy.newcld.pokemon.local.room.AppDatabase
import brandy.newcld.pokemon.local.room.EvolutionChainDao
import brandy.newcld.pokemon.local.room.PokemonDao
import brandy.newcld.pokemon.local.room.RoomConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `${RoomConstant.Table.ABILITY}` " +
                "(`name` TEXT NOT NULL, `ko_name` TEXT NOT NULL, PRIMARY KEY(`name`))"
        )
    }
}

private val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS `${RoomConstant.Table.EVOLUTION_CHAIN}` " +
                "(`chain_id` INTEGER NOT NULL, `stages_json` TEXT NOT NULL, PRIMARY KEY(`chain_id`))"
        )
    }
}

private val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("DELETE FROM `${RoomConstant.Table.EVOLUTION_CHAIN}`")
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal object LocalRoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = RoomConstant.ROOM_DB_NAME
        )
            .createFromAsset("pokemon.db")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
            .build()

    @Provides
    @Singleton
    fun providePokemonDao(database: AppDatabase): PokemonDao = database.pokemonDao()

    @Provides
    @Singleton
    fun provideAbilityDao(database: AppDatabase): AbilityDao = database.abilityDao()

    @Provides
    @Singleton
    fun provideEvolutionChainDao(database: AppDatabase): EvolutionChainDao = database.evolutionChainDao()
}