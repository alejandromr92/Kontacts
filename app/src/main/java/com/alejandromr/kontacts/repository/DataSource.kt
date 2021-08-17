package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ResultsModel

interface DataSource {

    suspend fun retrieveContacts(): Result<ResultsModel>
}
