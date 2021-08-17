package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.ContactsRepository
import com.alejandromr.kontacts.domain.model.ResultsModel

class ContactsRepositoryImpl(private val contactsDataSource: ContactsDataSource) : ContactsRepository {

    override suspend fun retrieveData(): Result<ResultsModel> =
        contactsDataSource.retrieveContacts()

    override suspend fun deleteContact(contact: ContactModel) =
        contactsDataSource.deleteContact(contact)

}
