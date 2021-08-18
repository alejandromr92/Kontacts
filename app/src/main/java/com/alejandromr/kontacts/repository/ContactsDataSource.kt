package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.domain.model.ContactModel

interface ContactsDataSource {

    suspend fun retrieveContactsFromApi(): Set<ContactModel>

    suspend fun retrieveContactsFromDatabase(): Set<ContactModel>

    suspend fun deleteContact(contactModel: ContactModel)
}
