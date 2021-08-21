package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest
import org.junit.Assert.*
import org.junit.Test

class ResultsApiModelTest {

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

        val resultsApiModel = ResultsApiModel(setOf(contactApiModel))

        with(resultsApiModel) {
            assertEquals("name", results?.firstOrNull()?.name?.first)
            assertEquals("surname", results?.firstOrNull()?.name?.last)
            assertEquals("name", results?.firstOrNull()?.location?.street?.name)
            assertEquals("66", results?.firstOrNull()?.location?.street?.number)
            assertEquals("city", results?.firstOrNull()?.location?.city)
            assertEquals("state", results?.firstOrNull()?.location?.state)
            assertEquals("state", results?.firstOrNull()?.location?.state)
            assertEquals("large", results?.firstOrNull()?.picture?.large)
            assertEquals("medium", results?.firstOrNull()?.picture?.medium)
            assertEquals("thumbnail", results?.firstOrNull()?.picture?.thumbnail)
            assertEquals("date", results?.firstOrNull()?.registered?.date)
            assertEquals("male", results?.firstOrNull()?.gender)
            assertEquals("phone", results?.firstOrNull()?.phone)
            assertEquals("mail", results?.firstOrNull()?.email)
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
        val resultsApiModel = ResultsApiModel(setOf(contactApiModel))

        val fromJson =
            GsonFactoryTest.gSon.fromJson(ResultsJson.RESULTS_JSON, ResultsApiModel::class.java)

        assertEquals(resultsApiModel, fromJson)
    }
}
