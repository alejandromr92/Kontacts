package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest
import org.junit.Assert.*
import org.junit.Test

class PictureApiModelTest {

    @Test
    fun `constructor initializes as expected`(){
        val pictureApiModel = PictureApiModel("large", "medium", "thumbnail")

        with(pictureApiModel){
            assertEquals("large", large)
            assertEquals("medium", medium)
            assertEquals("thumbnail", thumbnail)
        }
    }

    @Test
    fun `object can be successfully serialized into a JSON`() {
        val pictureApiModel = PictureApiModel("large", "medium", "thumbnail")

        val fromJson =
            GsonFactoryTest.gSon.fromJson(ResultsJson.PICTURE_JSON, PictureApiModel::class.java)

        assertEquals(pictureApiModel, fromJson)
    }
}
