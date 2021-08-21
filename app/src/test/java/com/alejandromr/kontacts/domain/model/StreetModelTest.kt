package com.alejandromr.kontacts.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class StreetModelTest {

    @Test
    fun `initializes as expected with parameters`() {
        val model = StreetModel(
            name = "name",
            number = "66"
        )

        with(model) {
            assertEquals("name", name)
            assertEquals("66", number)
        }
    }
}
