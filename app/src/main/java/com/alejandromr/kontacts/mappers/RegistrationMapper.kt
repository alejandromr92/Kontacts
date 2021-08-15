package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.ContactApiModel
import com.alejandromr.kontacts.api.RegistrationApiModel
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.RegistrationModel

class RegistrationMapper : Mapper<RegistrationApiModel, RegistrationModel> {
    override fun map(from: RegistrationApiModel?): RegistrationModel = RegistrationModel(
        from?.date ?: ""
    )
}
