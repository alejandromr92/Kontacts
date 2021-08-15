package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class LocationApiModel(
    @SerializedName("street")
    val street: StreetApiModel?,

    @SerializedName("city")
    val city: String?,

    @SerializedName("state")
    val state: String?
)
