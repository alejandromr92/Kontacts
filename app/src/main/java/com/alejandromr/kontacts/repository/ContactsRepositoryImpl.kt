package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.domain.ContactsRepository
import com.alejandromr.kontacts.domain.model.ContactModel

class ContactsRepositoryImpl(private val contactsDataSource: ContactsDataSource) :
    ContactsRepository {

    override suspend fun retrieveData(forceApi: Boolean): Set<ContactModel> {

        val contactsFromDatabase = contactsDataSource.retrieveContactsFromDatabase()

        return if (contactsFromDatabase.isEmpty() || forceApi) {
            contactsDataSource.retrieveContactsFromApi()
        } else {
            contactsFromDatabase
        }
    }


    override suspend fun deleteContact(contact: ContactModel) =
        contactsDataSource.deleteContact(contact)

}

