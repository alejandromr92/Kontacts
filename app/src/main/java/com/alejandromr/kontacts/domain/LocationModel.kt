package com.alejandromr.kontacts.domain

data class LocationModel(
    val street: StreetModel,
    val city: String,
    val state: String
)
