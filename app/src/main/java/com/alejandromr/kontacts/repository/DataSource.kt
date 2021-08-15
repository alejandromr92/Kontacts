package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.ResultsModel

interface DataSource {

    suspend fun retrieveContacts(): Result<ResultsModel>
}
