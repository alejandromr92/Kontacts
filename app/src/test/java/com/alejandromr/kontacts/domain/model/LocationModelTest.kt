package com.alejandromr.kontacts.domain.model

import junit.framework.Assert.assertEquals
import org.junit.Test

class LocationModelTest {
    @Test
    fun `initializes as expected with parameters`() {
        val streetModel = StreetModel("name", "66")
        val model = LocationModel(streetModel, "city", "state")

        with(model) {
            assertEquals("name", street.name)
            assertEquals("66", street.number)
            assertEquals("city", city)
            assertEquals("state", state)
        }
    }
}
