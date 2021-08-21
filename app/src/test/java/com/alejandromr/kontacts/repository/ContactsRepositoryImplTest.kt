package com.alejandromr.kontacts.repository

import com.alejandromr.kontacts.api.Failure
import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.domain.model.ContactModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ContactsRepositoryImplTest {

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @MockK
    private lateinit var dataSource: ContactsDataSource

    @MockK
    private lateinit var contactModel: ContactModel

    private val repository by lazy {
        ContactsRepositoryImpl(dataSource)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve data from database success flow`() {
        runBlockingTest {
            coEvery { dataSource.retrieveContactsFromDatabase() } returns Success(setOf(contactModel))

            repository.retrieveData(false)

            coVerifySequence {
                dataSource.retrieveContactsFromDatabase()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve data from database failure flow when forcing from api`() {
        runBlockingTest {
            coEvery { dataSource.retrieveContactsFromDatabase() } returns Failure()

            repository.retrieveData(true)

            coVerifySequence {
                dataSource.retrieveContactsFromDatabase()
                dataSource.retrieveContactsFromApi()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve data from api success flow`() {
        runBlockingTest {
            coEvery { dataSource.retrieveContactsFromDatabase() } returns Success(setOf(contactModel))

            repository.retrieveData(true)

            coVerifySequence {
                dataSource.retrieveContactsFromDatabase()
                dataSource.retrieveContactsFromApi()
            }
        }
    }
}
