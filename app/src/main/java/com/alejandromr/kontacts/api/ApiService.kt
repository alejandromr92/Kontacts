package com.alejandromr.kontacts.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun retrieveContacts(
        @Query(RESULTS_KEY) results: Int = RESULTS_TO_RETRIEVE,
        @Query(INCLUDE_KEY) dataToInclude: String = DATA_TO_INCLUDE
    ): ResultsApiModel

    companion object {
        const val RESULTS_KEY = "results"
        const val INCLUDE_KEY = "inc"
        const val RESULTS_TO_RETRIEVE = 40
        const val DATA_TO_INCLUDE = "name,location,email,registered,picture,gender,phone"
    }
}
