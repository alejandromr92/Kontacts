package com.alejandromr.kontacts.api

import com.alejandromr.kontacts.domain.model.ResultsModel
import com.alejandromr.kontacts.mappers.ResultMapper
import com.alejandromr.kontacts.networkclient.NetworkClient
import com.alejandromr.kontacts.repository.DataSource

class DataSourceImpl(
    networkClient: NetworkClient,
    private val resultMapper: ResultMapper
) : DataSource {

    private val service: ApiService = networkClient.retrofit.create(ApiService::class.java)

    override suspend fun retrieveContacts(): Result<ResultsModel> =
        safeApiCall {
            resultMapper.map(service.retrieveContacts())
        }

}
