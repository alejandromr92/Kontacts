package com.alejandromr.kontacts.datasource

import com.alejandromr.kontacts.api.ContactsApiService
import com.alejandromr.kontacts.api.Failure
import com.alejandromr.kontacts.api.Success
import com.alejandromr.kontacts.api.model.ContactApiModel
import com.alejandromr.kontacts.api.model.LocationApiModel
import com.alejandromr.kontacts.api.model.NameApiModel
import com.alejandromr.kontacts.api.model.PictureApiModel
import com.alejandromr.kontacts.api.model.RegistrationApiModel
import com.alejandromr.kontacts.api.model.ResultsApiModel
import com.alejandromr.kontacts.api.model.StreetApiModel
import com.alejandromr.kontacts.api.safeApiCall
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import com.alejandromr.kontacts.domain.model.ResultsModel
import com.alejandromr.kontacts.domain.model.StreetModel
import com.alejandromr.kontacts.mappers.ContactMapper
import com.alejandromr.kontacts.mappers.ResultMapper
import com.alejandromr.kontacts.networkclient.NetworkClient
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import retrofit2.Retrofit

class ContactsDataSourceImplTest {

    init {
        MockKAnnotations.init(this, true)
    }

    @MockK
    private lateinit var networkClient: NetworkClient

    @MockK
    private lateinit var retrofit: Retrofit

    @MockK
    private lateinit var contactsApiService: ContactsApiService

    @MockK
    private lateinit var contactsDao: ContactsDao

    @MockK
    private lateinit var resultMapper: ResultMapper

    @MockK
    private lateinit var contactsMapper: ContactMapper

    private val dataSource by lazy {
        ContactsDataSourceImpl(networkClient, contactsDao, resultMapper, contactsMapper)
    }

    @Before
    fun setup() {
        every { networkClient.retrofit } returns retrofit
        every { retrofit.create(ContactsApiService::class.java) } returns contactsApiService
        every { resultMapper.map(any()) } returns ResultsModel(setOf())
        every { contactsMapper.mapToDatabaseModelList(any())} returns listOf()
        every { contactsMapper.mapFromDatabaseModelList(any())} returns setOf()
        coEvery { contactsDao.retrieveContacts() } returns listOf()
        coEvery { contactsDao.saveRetrievedContacts(any()) } returns Unit
        coEvery { contactsDao.deleteContact("email") } returns Unit
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve contacts from api flow when success`() {
        runBlockingTest {
            coEvery { contactsApiService.retrieveContacts() } returns ResultsApiModel(setOf())

            dataSource.retrieveContactsFromApi()

            coVerifySequence {
                contactsApiService.retrieveContacts()
                resultMapper.map(any())
                contactsMapper.mapToDatabaseModelList(any())
                contactsDao.saveRetrievedContacts(any())
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve contacts from api flow when failure`() {
        runBlockingTest {
            coEvery { contactsApiService.retrieveContacts()  } throws Exception()

            dataSource.retrieveContactsFromApi()

            coVerifySequence {
                contactsApiService.retrieveContacts()
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `retrieve contacts from database calls contactsDao retrieveContacts method`() {
        runBlockingTest {
            dataSource.retrieveContactsFromDatabase()

            coVerifySequence {
                contactsDao.retrieveContacts()
                contactsMapper.mapFromDatabaseModelList(setOf())
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `delete contact from database calls contactsDao deleteContact method`() {
        runBlockingTest {
            val nameModel = NameModel("name", "surname")
            val streetModel = StreetModel("name", "66")
            val locationModel = LocationModel(streetModel, "city", "state")
            val pictureModel = PictureModel("large", "medium", "thumbnail")
            val registrationModel = RegistrationModel("date")
            val contactModel = ContactModel(
                nameModel,
                "male",
                locationModel,
                registrationModel,
                "phone",
                "email",
                pictureModel
            )

            dataSource.deleteContact(contactModel)

            coVerify(exactly = 1) {
                contactsDao.deleteContact("email")
            }
        }
    }
}
