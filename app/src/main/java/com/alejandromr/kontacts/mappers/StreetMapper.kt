package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.StreetApiModel
import com.alejandromr.kontacts.domain.model.StreetModel

class StreetMapper : Mapper<StreetApiModel, StreetModel> {
    override fun map(from: StreetApiModel?): StreetModel = StreetModel(
        from?.number ?: "",
        from?.name ?: ""
    )
}
