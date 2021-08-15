package com.alejandromr.kontacts.domain

import com.alejandromr.kontacts.api.Result

interface Repository {

    suspend fun retrieveData(): Result<ResultsModel>
}
