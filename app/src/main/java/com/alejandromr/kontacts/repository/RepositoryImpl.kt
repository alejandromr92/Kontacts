package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.Repository
import com.alejandromr.kontacts.domain.ResultsModel

class RepositoryImpl(private val dataSource: DataSource) : Repository {

    override suspend fun retrieveData(): Result<ResultsModel> =
        dataSource.retrieveContacts()

}
