package brandy.newcld.pokemon.data.model

import brandy.newcld.pokemon.data.DataMapper
import brandy.newcld.pokemon.domain.model.Moves

data class MovesEntity(
    val move: NameUrlEntity
): DataMapper<Moves> {
    override fun toDomain(): Moves = Moves(move.toDomain())
}
