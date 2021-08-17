package com.alejandromr.kontacts.domain.usecase

import com.alejandromr.kontacts.domain.AppDispatchers
import kotlinx.coroutines.withContext

class GetContactsUseCase(
    private val appDispatchers: AppDispatchers,
    private val contactsRepository: ContactsRepository
) {

    suspend operator fun invoke() = withContext(appDispatchers.io) {
        contactsRepository.retrieveData()
    }
}
