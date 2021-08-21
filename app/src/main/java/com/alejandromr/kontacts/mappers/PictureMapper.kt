package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.PictureApiModel
import com.alejandromr.kontacts.domain.model.PictureModel

class PictureMapper : Mapper<PictureApiModel, PictureModel> {
    override fun map(from: PictureApiModel?): PictureModel = PictureModel(
        from?.large ?: "",
        from?.medium ?: "",
        from?.thumbnail ?: ""
    )
}
