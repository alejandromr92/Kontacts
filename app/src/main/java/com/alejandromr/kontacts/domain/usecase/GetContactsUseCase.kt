package com.alejandromr.kontacts.domain.usecase

import com.alejandromr.kontacts.domain.AppDispatchers
import com.alejandromr.kontacts.domain.ContactsRepository
import com.alejandromr.kontacts.domain.model.ContactModel
import kotlinx.coroutines.withContext

class GetContactsUseCase(
    private val appDispatchers: AppDispatchers,
    private val contactsRepository: ContactsRepository
) {

    suspend operator fun invoke(forceApi: Boolean = true): Set<ContactModel> =
        withContext(appDispatchers.io) {
            contactsRepository.retrieveData(forceApi)
        }
}
