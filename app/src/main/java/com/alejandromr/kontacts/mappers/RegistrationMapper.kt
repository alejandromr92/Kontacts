package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.RegistrationApiModel
import com.alejandromr.kontacts.domain.model.RegistrationModel

class RegistrationMapper : Mapper<RegistrationApiModel, RegistrationModel> {
    override fun map(from: RegistrationApiModel?): RegistrationModel = RegistrationModel(
        from?.date ?: ""
    )
}
