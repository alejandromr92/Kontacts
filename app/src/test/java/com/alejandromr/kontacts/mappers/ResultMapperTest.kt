package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.ContactApiModel
import com.alejandromr.kontacts.api.model.ResultsApiModel
import com.alejandromr.kontacts.domain.model.ContactModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResultMapperTest {

    init {
        MockKAnnotations.init(this)
    }

    @MockK
    private lateinit var contactMapper: ContactMapper

    @MockK
    private lateinit var contactModel: ContactModel

    @MockK
    private lateinit var contactApiModel: ContactApiModel

    private val mapper by lazy {
        ResultMapper(contactMapper)
    }

    @Before
    fun setup() {
        every { contactMapper.map(contactApiModel) } returns contactModel
    }

    @Test
    fun `map null received values from api to default ones`() {
        val apiModel = ResultsApiModel()

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals(setOf<ContactModel>(), results)
        }
    }

    @Test
    fun `map non null received values from api to default ones`() {
        val apiModel = ResultsApiModel(setOf(contactApiModel))

        val model = mapper.map(apiModel)

        with(model) {
            assertEquals(setOf(contactModel), results)
        }
    }
}
