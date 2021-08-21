package com.alejandromr.kontacts.domain.usecase

import com.alejandromr.kontacts.domain.AppDispatchers
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import com.alejandromr.kontacts.domain.model.StreetModel
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

class DeleteContactUseCaseTest {

    init {
        MockKAnnotations.init(this, relaxed = true)
    }

    @MockK
    private lateinit var appDispatchers: AppDispatchers

    @MockK
    private lateinit var contactsRepository: ContactsRepositoryImpl

    private val usecase by lazy {
        DeleteContactUseCase(appDispatchers, contactsRepository)
    }

    @Before
    fun setup() {
        coEvery { appDispatchers.io } returns Dispatchers.Unconfined
        coEvery { contactsRepository.deleteContact(any()) } returns Unit
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `invoke() must call repository retrieveData method`() {
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
                "mail",
                pictureModel
            )

            usecase(contactModel)
            coVerify(exactly = 1) { contactsRepository.deleteContact(any()) }
        }
    }
}
