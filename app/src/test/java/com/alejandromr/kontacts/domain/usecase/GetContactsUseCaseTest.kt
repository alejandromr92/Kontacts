package com.alejandromr.kontacts.domain.usecase

import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.domain.AppDispatchers
import com.alejandromr.kontacts.repository.ContactsRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any

class GetContactsUseCaseTest {

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @MockK
    private lateinit var appDispatchers: AppDispatchers

    @MockK
    private lateinit var contactsRepository: ContactsRepositoryImpl

    private val usecase by lazy {
        GetContactsUseCase(appDispatchers, contactsRepository)
    }

    @Before
    fun setup() {
        coEvery { appDispatchers.io } returns Dispatchers.Unconfined
        coEvery { contactsRepository.retrieveData(true) } returns Success(any())
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `invoke() must call repository retrieveData method`() {
        runBlockingTest {
            usecase(true)
            coVerify(exactly = 1) { contactsRepository.retrieveData(true) }
        }
    }
}
