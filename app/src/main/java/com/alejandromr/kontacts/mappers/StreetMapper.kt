package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.StreetApiModel
import com.alejandromr.kontacts.domain.StreetModel

class StreetMapper : Mapper<StreetApiModel, StreetModel> {
    override fun map(from: StreetApiModel?): StreetModel = StreetModel(
        from?.number ?: "",
        from?.name ?: ""
    )
}
