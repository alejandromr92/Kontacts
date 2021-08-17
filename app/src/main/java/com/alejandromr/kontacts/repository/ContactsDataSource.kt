package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.model.ResultsModel

interface ContactsDataSource {

    suspend fun retrieveContacts(): Result<ResultsModel>

    suspend fun deleteContact(contactModel: ContactModel)
}
