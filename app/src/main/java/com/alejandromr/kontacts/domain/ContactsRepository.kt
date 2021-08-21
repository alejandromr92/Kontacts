package com.alejandromr.kontacts.domain

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ContactModel

interface ContactsRepository {

    suspend fun retrieveData(forceApi: Boolean): Result<Set<ContactModel>>

    suspend fun deleteContact(contact: ContactModel)
}
