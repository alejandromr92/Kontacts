package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class LocationApiModel(
    @SerializedName("street")
    val street: StreetApiModel? = null,

    @SerializedName("city")
    val city: String? = null,

    @SerializedName("state")
    val state: String? = null
)
