package com.alejandromr.kontacts.api.model

import com.alejandromr.kontacts.api.GsonFactoryTest
import org.junit.Assert.*
import org.junit.Test

class NameApiModelTest {

    @Test
    fun `constructor initializes as expected`(){
        val nameApiModel = NameApiModel("name", "surname")

        with(nameApiModel){
            assertEquals("name", first)
            assertEquals("surname", last)
        }
    }

    @Test
    fun `object can be successfully serialized into a JSON`() {
        val nameApiModel = NameApiModel("name", "surname")

        val fromJson =
            GsonFactoryTest.gSon.fromJson(ResultsJson.NAME_JSON, NameApiModel::class.java)

        assertEquals(nameApiModel, fromJson)
    }
}
