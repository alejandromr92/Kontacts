package com.alejandromr.kontacts.domain

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ResultsModel

interface Repository {

    suspend fun retrieveData(): Result<ResultsModel>
}
