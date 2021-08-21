package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.StreetApiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class StreetMapperTest {

    private val mapper by lazy {
        StreetMapper()
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = StreetApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("", name)
            assertEquals("", number)
        }
    }

    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = StreetApiModel("name", "66")

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("name", name)
            assertEquals("66", number)
        }
    }
}
