package com.alejandromr.kontacts.mappers

import com.alejandromr.kontacts.api.model.LocationApiModel
import com.alejandromr.kontacts.domain.model.LocationModel

class LocationMapper(private val streetMapper: StreetMapper) : Mapper<LocationApiModel, LocationModel> {
    override fun map(from: LocationApiModel?): LocationModel = LocationModel(
        streetMapper.map(from?.street),
        from?.city ?: "",
        from?.state ?: ""
    )
}
