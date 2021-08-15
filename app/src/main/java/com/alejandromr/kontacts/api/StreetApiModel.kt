package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class StreetApiModel(
    @SerializedName("number")
    val number: String?,

    @SerializedName("name")
    val name: String?
)
