package com.alejandromr.kontacts.domain.model

import junit.framework.Assert.assertEquals
import org.junit.Test

class ContactModelTest {
    @Test
    fun `initializes as expected with parameters`() {
        val nameModel = NameModel("name", "surname")
        val streetModel = StreetModel("name", "66")
        val locationModel = LocationModel(streetModel, "city", "state")
        val pictureModel = PictureModel("large", "medium", "thumbnail")
        val registrationModel = RegistrationModel("date")
        val model = ContactModel(
            nameModel,
            "male",
            locationModel,
            registrationModel,
            "phone",
            "mail",
            pictureModel
        )

        with(model) {
            assertEquals("name", name.first)
            assertEquals("surname", name.last)
            assertEquals("name", location.street.name)
            assertEquals("66", location.street.number)
            assertEquals("city", location.city)
            assertEquals("state", location.state)
            assertEquals("state", location.state)
            assertEquals("large", picture.large)
            assertEquals("medium", picture.medium)
            assertEquals("thumbnail", picture.thumbnail)
            assertEquals("date", registered.date)
            assertEquals("male", gender)
            assertEquals("phone", phone)
            assertEquals("mail", email)
        }
    }
}
