package brandy.newcld.pokemon.dataresource

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

sealed class AppError {
    object Network : AppError()
    data class Server(val code: Int) : AppError()
    object Parse : AppError()
    object Empty : AppError()
    data class Unknown(val cause: Throwable) : AppError()
}

class EmptyDataException(message: String = "empty") : RuntimeException(message)

fun Throwable.toAppError(): AppError = when {
    this is EmptyDataException -> AppError.Empty
    this is UnknownHostException ||
        this is ConnectException ||
        this is SocketTimeoutException ||
        this is SSLException -> AppError.Network
    this is IOException -> AppError.Network
    isHttpException() -> AppError.Server(extractHttpCode() ?: -1)
    isJsonException() -> AppError.Parse
    else -> AppError.Unknown(this)
}

private fun Throwable.isHttpException(): Boolean =
    this::class.java.name == "retrofit2.HttpException"

private fun Throwable.extractHttpCode(): Int? = runCatching {
    this::class.java.getMethod("code").invoke(this) as? Int
}.getOrNull()

private fun Throwable.isJsonException(): Boolean {
    val name = this::class.java.name
    return name == "com.google.gson.JsonSyntaxException" ||
        name == "com.google.gson.stream.MalformedJsonException" ||
        name == "com.google.gson.JsonParseException"
}
