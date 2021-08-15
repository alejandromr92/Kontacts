package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.ContactApiModel
import com.alejandromr.kontacts.api.NameApiModel
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.NameModel

class NameMapper : Mapper<NameApiModel, NameModel> {


    override fun map(from: NameApiModel?): NameModel = NameModel(
        from?.first ?: "",
        from?.last ?: ""
    )

}
