package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.LocationApiModel
import com.alejandromr.kontacts.api.model.StreetApiModel
import com.alejandromr.kontacts.domain.model.StreetModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocationMapperTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var streetMapper: StreetMapper

    @MockK
    private lateinit var streetModel: StreetModel

    @MockK
    private lateinit var streetApiModel: StreetApiModel

    private val mapper by lazy {
        LocationMapper(streetMapper)
    }

    @Before
    fun setup() {
        every { streetMapper.map(streetApiModel) } returns streetModel
        every { streetMapper.map(null) } returns streetModel
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = LocationApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals("", city)
            assertEquals("", state)
            assertEquals(streetModel, street)
        }
    }


    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = LocationApiModel(streetApiModel, "city", "state")

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals(streetModel, street)
            assertEquals("city", city)
            assertEquals("state", state)
        }
    }
}
