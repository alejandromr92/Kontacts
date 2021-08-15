package com.alejandromr.kontacts.api

suspend fun <T> safeApiCall(
    call: suspend () -> T
): Result<T> =
    try {
        val response = call()

        if (response == null) {
            Failure()
        } else {
            Success(response)
        }
    } catch (exception: Exception) {
        Failure()
    }
