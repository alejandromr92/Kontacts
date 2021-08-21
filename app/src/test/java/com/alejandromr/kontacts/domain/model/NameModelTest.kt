package com.alejandromr.kontacts.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class NameModelTest {

    @Test
    fun `initializes as expected with parameters`() {
        val model = NameModel(
            first = "name",
            last = "last"
        )

        with(model) {
            assertEquals("name", first)
            assertEquals("last", last)
        }
    }
}
