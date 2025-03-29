package com.sinharahul.bookpedia.core.data

import com.sinharahul.bookpedia.core.domain.DataError
import com.sinharahul.bookpedia.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Remote> {
    val response = try {
        val httpResponse = execute()
        responseToResult<T>(httpResponse)
    } catch (ex: SocketTimeoutException) {
        Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (ex: UnresolvedAddressException) {
        Result.Error(DataError.Remote.NO_INTERNET)
    } catch (ex: Exception) {
        coroutineContext.ensureActive()
        Result.Error(DataError.Remote.UNKNOWN)
    }

    return response
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (ex: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }

        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}
