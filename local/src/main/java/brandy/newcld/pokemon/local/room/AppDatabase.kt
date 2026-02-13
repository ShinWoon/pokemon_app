package brandy.newcld.pokemon.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import brandy.newcld.pokemon.local.model.PokemonAppBarLocalEntity
import brandy.newcld.pokemon.local.room.RoomConstant.ROOM_DB_VERSION

@Database(entities = [PokemonAppBarLocalEntity::class], version = ROOM_DB_VERSION)

abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}