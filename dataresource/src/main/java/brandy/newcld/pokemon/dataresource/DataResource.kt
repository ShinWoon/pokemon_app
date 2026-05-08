package brandy.newcld.pokemon.dataresource

sealed class DataResource<out T> {
    class Success<T>(val data: T) : DataResource<T>()
    class Error(val error: AppError) : DataResource<Nothing>()
    class Loading<T>(val data: T? = null) : DataResource<T>()

    companion object {
        fun <T> success(data: T) =
            Success(data)

        fun error(error: AppError) =
            Error(error)

        fun error(throwable: Throwable) =
            Error(throwable.toAppError())

        fun <T> loading(data: T? = null): Loading<T> =
            Loading(data)
    }
}
