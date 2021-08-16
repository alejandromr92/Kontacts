package com.alejandromr.kontacts.domain

import java.io.Serializable

data class LocationModel(
    val street: StreetModel,
    val city: String,
    val state: String
) : Serializable
