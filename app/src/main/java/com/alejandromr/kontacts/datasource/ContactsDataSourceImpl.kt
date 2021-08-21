package com.alejandromr.kontacts.datasource

import com.alejandromr.kontacts.api.ApiService
import com.alejandromr.kontacts.api.Failure
import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.api.model.ResultsApiModel
import com.alejandromr.kontacts.api.safeApiCall
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.mappers.ContactMapper
import com.alejandromr.kontacts.mappers.ResultMapper
import com.alejandromr.kontacts.networkclient.NetworkClient
import com.alejandromr.kontacts.repository.ContactsDataSource

class ContactsDataSourceImpl(
    networkClient: NetworkClient,
    private val contactsDao: ContactsDao,
    private val resultMapper: ResultMapper,
    private val contactsMapper: ContactMapper
) : ContactsDataSource {

    private val service: ApiService = networkClient.retrofit.create(ApiService::class.java)

    override suspend fun retrieveContactsFromApi(): Result<Set<ContactModel>> {
        val contactsRetrieved = safeApiCall {
            resultMapper.map(service.retrieveContacts())
        }

        return if (contactsRetrieved is Success) {
            contactsDao.saveRetrievedContacts(
                contactsMapper.mapToDatabaseModelList(
                    contactsRetrieved.result.results.toList()
                )
            )
            Success((contactsRetrieved as? Success)?.result?.results ?: emptySet())
        } else {
            Failure()
        }
    }

    override suspend fun retrieveContactsFromDatabase(): Result<Set<ContactModel>> =
        Success(contactsMapper.mapFromDatabaseModelList(contactsDao.retrieveContacts().toSet()))

    override suspend fun deleteContact(contactModel: ContactModel) =
        contactsDao.deleteContact(contactModel.email)


}
