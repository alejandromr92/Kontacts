package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class StreetApiModel(
    @SerializedName("name")
    val name: String? = null,

    @SerializedName("number")
    val number: String? = null

)
