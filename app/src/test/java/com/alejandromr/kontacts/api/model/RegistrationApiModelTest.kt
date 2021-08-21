package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest
import org.junit.Assert.*
import org.junit.Test

class RegistrationApiModelTest {

    @Test
    fun `constructor initializes as expected`(){
        val registrationApiModel = RegistrationApiModel("date")

        with(registrationApiModel){
            assertEquals("date", date)
        }
    }

    @Test
    fun `object can be successfully serialized into a JSON`() {
        val registrationApiModel = RegistrationApiModel("date")

        val fromJson =
            GsonFactoryTest.gSon.fromJson(ResultsJson.REGISTRATION_JSON, RegistrationApiModel::class.java)

        assertEquals(registrationApiModel, fromJson)
    }
}
