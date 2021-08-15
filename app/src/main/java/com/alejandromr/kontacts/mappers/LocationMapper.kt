package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.ContactApiModel
import com.alejandromr.kontacts.api.LocationApiModel
import com.alejandromr.kontacts.domain.ContactModel
import com.alejandromr.kontacts.domain.LocationModel

class LocationMapper(private val streetMapper: StreetMapper) : Mapper<LocationApiModel, LocationModel> {
    override fun map(from: LocationApiModel?): LocationModel = LocationModel(
        streetMapper.map(from?.street),
        from?.city ?: "",
        from?.state ?: ""
    )
}
