package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.NameApiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class NameMapperTest {

    private val mapper by lazy {
        NameMapper()
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = NameApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("", first)
            assertEquals("", last)
        }
    }


    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = NameApiModel("name", "surname")

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("name", first)
            assertEquals("surname", last)
        }
    }
}
