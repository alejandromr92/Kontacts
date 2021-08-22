package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactApiModelTest {

    @Test
    fun `constructor initializes as expected`() {
        val nameApiModel = NameApiModel("name", "surname")
        val streetApiModel = StreetApiModel("name", "66")
        val locationApiModel = LocationApiModel(streetApiModel, "city", "state")
        val pictureApiModel = PictureApiModel("large", "medium", "thumbnail")
        val registrationApiModel = RegistrationApiModel("date")
        val contactApiModel = ContactApiModel(
            nameApiModel,
            "male",
            locationApiModel,
            registrationApiModel,
            "phone",
            "mail",
            pictureApiModel
        )

        with(contactApiModel){
            assertEquals("name", name?.first)
            assertEquals("surname", name?.last)
            assertEquals("name", location?.street?.name)
            assertEquals("66", location?.street?.number)
            assertEquals("city", location?.city)
            assertEquals("state", location?.state)
            assertEquals("state", location?.state)
            assertEquals("large", picture?.large)
            assertEquals("medium", picture?.medium)
            assertEquals("thumbnail", picture?.thumbnail)
            assertEquals("date", registered?.date)
            assertEquals("male", gender)
            assertEquals("phone", phone)
            assertEquals("mail", email)
        }
    }

    @Test
    fun `object can be successfully serialized into a JSON`() {
        val nameApiModel = NameApiModel("name", "surname")
        val streetApiModel = StreetApiModel("name", "66")
        val locationApiModel = LocationApiModel(streetApiModel, "city", "state")
        val pictureApiModel = PictureApiModel("large", "medium", "thumbnail")
        val registrationApiModel = RegistrationApiModel("date")
        val contactApiModel = ContactApiModel(
            nameApiModel,
            "male",
            locationApiModel,
            registrationApiModel,
            "phone",
            "mail",
            pictureApiModel
        )

        val fromJson =
            GsonFactoryTest.gSon.fromJson(ResultsJson.CONTACT_JSON, ContactApiModel::class.java)

        assertEquals(contactApiModel, fromJson)
    }
}
