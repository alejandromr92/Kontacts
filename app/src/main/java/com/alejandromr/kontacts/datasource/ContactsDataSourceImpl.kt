package com.alejandromr.kontacts.datasource

import com.alejandromr.kontacts.api.ApiService
import com.alejandromr.kontacts.api.Success
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

    override suspend fun retrieveContactsFromApi(): Set<ContactModel> {
        val contactsRetrieved = safeApiCall {
            resultMapper.map(service.retrieveContacts())
        }

        if (contactsRetrieved is Success) {
            contactsDao.saveRetrievedContacts(
                contactsMapper.mapToDatabaseModelList(
                    contactsRetrieved.result.results.toList()
                )
            )
        }

        return (contactsRetrieved as? Success)?.result?.results ?: emptySet()
    }

    override suspend fun retrieveContactsFromDatabase(): Set<ContactModel> =
        contactsMapper.mapFromDatabaseModelList(contactsDao.retrieveContacts().toSet())

    override suspend fun deleteContact(contactModel: ContactModel) =
        contactsDao.deleteContact(contactModel.email)


}
