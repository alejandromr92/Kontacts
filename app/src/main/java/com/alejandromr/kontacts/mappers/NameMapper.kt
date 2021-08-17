package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.NameApiModel
import com.alejandromr.kontacts.domain.model.NameModel

class NameMapper : Mapper<NameApiModel, NameModel> {


    override fun map(from: NameApiModel?): NameModel = NameModel(
        from?.first ?: "",
        from?.last ?: ""
    )

}
