package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest
import org.junit.Assert.*
import org.junit.Test

class LocationApiModelTest {
    @Test
    fun `constructor initializes as expected`(){
        val streetApiModel = StreetApiModel("name", "66")
        val locationApiModel = LocationApiModel(streetApiModel, "city", "state")

        with(locationApiModel){
            assertEquals("name", street?.name)
            assertEquals("66", street?.number)
            assertEquals("city", city)
            assertEquals("state", state)
        }
    }

    @Test
    fun `object can be successfully serialized into a JSON`() {
        val streetApiModel = StreetApiModel("name", "66")
        val locationApiModel = LocationApiModel(streetApiModel, "city", "state")

        val fromJson =
            GsonFactoryTest.gSon.fromJson(ResultsJson.LOCATION_JSON, LocationApiModel::class.java)

        assertEquals(locationApiModel, fromJson)
    }
}
