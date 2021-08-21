package com.alejandromr.kontacts.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class PictureModelTest {

    @Test
    fun `initializes as expected with parameters`() {
        val model = PictureModel(
            thumbnail = "thumbnail",
            medium = "medium",
            large = "large"
        )

        with(model) {
            assertEquals("thumbnail", thumbnail)
            assertEquals("medium", medium)
            assertEquals("large", large)
        }
    }
}
