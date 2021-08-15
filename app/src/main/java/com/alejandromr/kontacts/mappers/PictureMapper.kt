package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.ContactApiModel
import com.alejandromr.kontacts.api.PictureApiModel
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.PictureModel

class PictureMapper: Mapper<PictureApiModel, PictureModel>  {
    override fun map(from: PictureApiModel?): PictureModel = PictureModel(from?.large ?: "",
    from?.medium ?: "",
    from?.thumbnail ?: "")
}
