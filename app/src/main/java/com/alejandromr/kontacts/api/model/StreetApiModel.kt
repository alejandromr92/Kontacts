package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class StreetApiModel(
    @SerializedName("name")
    val name: String?,

    @SerializedName("number")
    val number: String?

)
