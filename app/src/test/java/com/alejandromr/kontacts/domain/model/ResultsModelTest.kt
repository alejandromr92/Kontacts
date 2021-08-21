package com.alejandromr.kontacts.domain.model

import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultsModelTest {

    @Test
    fun `initializes as expected with parameters`() {
        val model = ResultsModel(
            setOf(mockk())
        )

        with(model) {
            assertEquals(1, results.size)
        }
    }
}
