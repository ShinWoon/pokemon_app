package brandy.newcld.pokemon.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import brandy.newcld.pokemon.local.model.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}