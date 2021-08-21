package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.PictureApiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class PictureMapperTest {

    private val mapper by lazy {
        PictureMapper()
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = PictureApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("", large)
            assertEquals("", medium)
            assertEquals("", thumbnail)
        }
    }


    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = PictureApiModel("large", "medium", "thumbnail")

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("large", large)
            assertEquals("medium", medium)
            assertEquals("thumbnail", thumbnail)
        }
    }
}
