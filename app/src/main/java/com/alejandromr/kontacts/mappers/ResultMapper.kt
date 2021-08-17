package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.ResultsApiModel
import com.alejandromr.kontacts.domain.model.ResultsModel

class ResultMapper(private val contactMapper: ContactMapper) :
    Mapper<ResultsApiModel, ResultsModel> {

    override fun map(from: ResultsApiModel?): ResultsModel =
        ResultsModel(from?.results?.map { contactMapper.map(it) }?.toSet() ?: emptySet())

}
