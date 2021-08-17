package com.alejandromr.kontacts.domain.usecase

import com.alejandromr.kontacts.domain.AppDispatchers
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.ContactsRepository
import kotlinx.coroutines.withContext

class DeleteContactUseCase(
    private val appDispatchers: AppDispatchers,
    private val contactsRepository: ContactsRepository
) {

    suspend operator fun invoke(contact: ContactModel) = withContext(appDispatchers.io) {
        contactsRepository.deleteContact(contact)
    }
}
