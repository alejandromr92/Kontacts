package com.alejandromr.kontacts.domain

import com.alejandromr.kontacts.api.Result
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.model.ResultsModel

interface ContactsRepository {

    suspend fun retrieveData(): Result<ResultsModel>

    suspend fun deleteContact(contact: ContactModel)
}
