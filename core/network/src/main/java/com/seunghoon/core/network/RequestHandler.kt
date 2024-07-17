package com.seunghoon.core.network

import io.ktor.client.plugins.ClientRequestException

class RequestHandler<T> {
    suspend fun request(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: ClientRequestException) {
            when (e.response.status.value) {
                400 -> throw BadRequest
                401 -> throw Unauthorized
                404 -> throw NotFound
                else -> throw e
            }
        }
    }
}
