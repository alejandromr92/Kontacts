package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ContactModel

interface ContactsDataSource {

    suspend fun retrieveContactsFromApi(): Result<Set<ContactModel>>

    suspend fun retrieveContactsFromDatabase(): Result<Set<ContactModel>>

    suspend fun deleteContact(contactModel: ContactModel)
}
