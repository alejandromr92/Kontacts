package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.domain.ContactsRepository
import com.alejandromr.kontacts.domain.model.ContactModel

class ContactsRepositoryImpl(private val contactsDataSource: ContactsDataSource) :
    ContactsRepository {

    override suspend fun retrieveData(forceApi: Boolean): Result<Set<ContactModel>> {

        val contactsFromDatabase = contactsDataSource.retrieveContactsFromDatabase()

        return if (contactsFromDatabase is Success && contactsFromDatabase.result.isEmpty() || forceApi) {
            contactsDataSource.retrieveContactsFromApi()
        } else {
            contactsFromDatabase
        }
    }

    override suspend fun deleteContact(contact: ContactModel) =
        contactsDataSource.deleteContact(contact)

}

