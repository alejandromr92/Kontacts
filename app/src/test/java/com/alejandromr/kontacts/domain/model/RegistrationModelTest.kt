package com.alejandromr.kontacts.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class RegistrationModelTest {

    @Test
    fun `initializes as expected with parameters`() {
        val model = RegistrationModel(
            date = "date"
        )

        with(model) {
            assertEquals("date", date)
        }
    }
}
