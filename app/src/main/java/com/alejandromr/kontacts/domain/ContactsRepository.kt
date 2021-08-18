package com.alejandromr.kontacts.domain

import com.alejandromr.kontacts.domain.model.ContactModel

interface ContactsRepository {

    suspend fun retrieveData(forceApi: Boolean): Set<ContactModel>

    suspend fun deleteContact(contact: ContactModel)
}
