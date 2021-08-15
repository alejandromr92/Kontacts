package com.alejandromr.kontacts.domain

import kotlinx.coroutines.withContext

class GetContactsUseCase(
    private val appDispatchers: AppDispatchers,
    private val repository: Repository
) {

    suspend operator fun invoke() = withContext(appDispatchers.io) {
        repository.retrieveData()
    }
}
