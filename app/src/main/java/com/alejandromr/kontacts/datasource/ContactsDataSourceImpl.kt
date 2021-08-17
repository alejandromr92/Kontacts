package com.alejandromr.kontacts.datasource

import com.alejandromr.kontacts.api.ApiService
import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.api.safeApiCall
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.model.ResultsModel
import com.alejandromr.kontacts.mappers.ResultMapper
import com.alejandromr.kontacts.networkclient.NetworkClient
import com.alejandromr.kontacts.repository.ContactsDataSource

class ContactsDataSourceImpl(
    networkClient: NetworkClient,
    private val database: ContactsDatabase,
    private val resultMapper: ResultMapper
) : ContactsDataSource {

    private val service: ApiService = networkClient.retrofit.create(ApiService::class.java)

    override suspend fun retrieveContacts(): Result<ResultsModel> {
        val contactsRetrieved = safeApiCall {
            resultMapper.map(service.retrieveContacts())
        }

        if (contactsRetrieved is Success){
            database.contactsDao().saveRetrievedContacts(contactsRetrieved.result.results)
        }

        return contactsRetrieved
    }

    override suspend fun deleteContact(contactModel: ContactModel) =
        database.contactsDao().deleteContact(contactModel)



}
