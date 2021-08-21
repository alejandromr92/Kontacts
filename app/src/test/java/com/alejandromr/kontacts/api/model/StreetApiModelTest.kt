package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest.gSon
import org.junit.Assert.*
import org.junit.Test

class StreetApiModelTest {

    @Test
    fun `constructor initializes as expected`(){
        val streetApiModel = StreetApiModel("name", "66")

        with(streetApiModel){
            assertEquals("name", name)
            assertEquals("66", number)
        }
    }

    @Test
    fun `object can be successfully serialized into a JSON`() {
        val streetApiModel = StreetApiModel("name", "66")

        val fromJson =
            gSon.fromJson(ResultsJson.STREET_JSON, StreetApiModel::class.java)

        assertEquals(streetApiModel, fromJson)
    }
}
