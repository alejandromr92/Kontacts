package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.RegistrationApiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class RegistrationMapperTest {

    private val mapper by lazy {
        RegistrationMapper()
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = RegistrationApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("", date)
        }
    }


    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = RegistrationApiModel("date")

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("date", date)
        }
    }
}
