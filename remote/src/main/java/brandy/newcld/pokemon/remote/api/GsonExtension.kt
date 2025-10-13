package brandy.newcld.pokemon.remote.api

import com.google.gson.GsonBuilder

val gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd HH:mm:ss")
    .create()

fun Any.toJson(): String? = gson.toJson(this)

inline fun <reified T> String.fromJson(): T? =
    runCatching {
        gson.fromJson(this, T::class.java)
    }.onFailure {

    }.getOrNull()