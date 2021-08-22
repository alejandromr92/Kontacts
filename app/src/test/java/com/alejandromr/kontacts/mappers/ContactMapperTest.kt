package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.ContactApiModel
import com.alejandromr.kontacts.api.model.LocationApiModel
import com.alejandromr.kontacts.api.model.NameApiModel
import com.alejandromr.kontacts.api.model.PictureApiModel
import com.alejandromr.kontacts.api.model.RegistrationApiModel
import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ContactMapperTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var nameApiModel: NameApiModel

    @MockK
    private lateinit var nameModel: NameModel

    @MockK
    private lateinit var locationApiModel: LocationApiModel

    @MockK
    private lateinit var locationModel: LocationModel

    @MockK
    private lateinit var pictureApiModel: PictureApiModel

    @MockK
    private lateinit var pictureModel: PictureModel

    @MockK
    private lateinit var registrationApiModel: RegistrationApiModel

    @MockK
    private lateinit var registrationModel: RegistrationModel

    @MockK
    private lateinit var nameMapper: NameMapper

    @MockK
    private lateinit var locationMapper: LocationMapper

    @MockK
    private lateinit var registrationMapper: RegistrationMapper

    @MockK
    private lateinit var pictureMapper: PictureMapper

    private val mapper by lazy {
        ContactMapper(nameMapper, locationMapper, registrationMapper, pictureMapper)
    }

    @Before
    fun setup() {
        every { nameMapper.map(nameApiModel) } returns nameModel
        every { nameMapper.map(null) } returns nameModel
        every { locationMapper.map(locationApiModel) } returns locationModel
        every { locationMapper.map(null) } returns locationModel
        every { registrationMapper.map(registrationApiModel) } returns registrationModel
        every { registrationMapper.map(null) } returns registrationModel
        every { pictureMapper.map(pictureApiModel) } returns pictureModel
        every { pictureMapper.map(null) } returns pictureModel
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = ContactApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals(nameModel, name)
            assertEquals("", gender)
            assertEquals(locationModel, location)
            assertEquals(registrationModel, registered)
            assertEquals("", phone)
            assertEquals("", email)
            assertEquals(pictureModel, picture)
            assertEquals(false, deleted)
        }
    }

    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = ContactApiModel(
            nameApiModel,
            "male",
            locationApiModel,
            registrationApiModel,
            "phone",
            "email",
            pictureApiModel
        )

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals(nameModel, name)
            assertEquals("male", gender)
            assertEquals(locationModel, location)
            assertEquals(registrationModel, registered)
            assertEquals("phone", phone)
            assertEquals("email", email)
            assertEquals(pictureModel, picture)
            assertEquals(false, deleted)
        }
    }
}
