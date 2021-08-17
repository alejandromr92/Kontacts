package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.ContactApiModel
import com.alejandromr.kontacts.domain.model.ContactModel

class ContactMapper(
    private val nameMapper: NameMapper,
    private val locationMapper: LocationMapper,
    private val registrationMapper: RegistrationMapper,
    private val pictureMapper: PictureMapper
) : Mapper<ContactApiModel, ContactModel> {

    override fun map(from: ContactApiModel?): ContactModel =
        ContactModel(
            nameMapper.map(from?.name),
            from?.gender ?: "", //TODO: SEALED CLASS GENDER
            locationMapper.map(from?.location),
            registrationMapper.map(from?.registered),
            from?.phone ?: "",
            from?.email ?: "",
            pictureMapper.map(from?.picture)
        )
}
