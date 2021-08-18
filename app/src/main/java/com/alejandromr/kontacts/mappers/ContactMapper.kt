package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.ContactApiModel
import com.alejandromr.kontacts.datasource.ContactDbModel
import com.alejandromr.kontacts.domain.model.ContactModel
import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import com.alejandromr.kontacts.domain.model.StreetModel

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

    fun mapToDatabaseModelList(from: List<ContactModel?>?): List<ContactDbModel> =
        from?.map { mapToDatabaseModel(it) } ?: emptyList()

    fun mapToDatabaseModel(from: ContactModel?): ContactDbModel =
        ContactDbModel(
            from?.name?.first ?: "",
            from?.name?.last ?: "",
            from?.gender ?: "",
            from?.location?.street?.name ?: "",
            from?.location?.street?.number ?: "",
            from?.location?.city ?: "",
            from?.location?.state ?: "",
            from?.registered?.date ?: "",
            from?.phone ?: "",
            from?.email ?: "",
            from?.picture?.thumbnail ?: "",
            from?.picture?.medium ?: "",
            from?.picture?.large ?: "",
        )

    fun mapFromDatabaseModelList(from: Set<ContactDbModel?>?): Set<ContactModel> =
        from?.map { mapFromDatabaseModel(it) }?.toSet() ?: emptySet()

    fun mapFromDatabaseModel(from: ContactDbModel?): ContactModel =
        ContactModel(
            NameModel(from?.name ?: "", from?.surname ?: ""),
            from?.gender ?: "",
            LocationModel(
                StreetModel(from?.streetName ?: "", from?.streetNumber ?: ""),
                from?.city ?: "",
                from?.state ?: ""
            ),
            RegistrationModel(from?.registeredDate ?: ""),
            from?.phone ?: "",
            from?.email ?: "",
            PictureModel(
                from?.pictureThumbnail ?: "",
                from?.pictureMedium ?: "",
                from?.pictureLarge ?: ""
            ),
            from?.deleted ?: false
        )

}
