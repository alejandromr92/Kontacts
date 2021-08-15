package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class NameApiModel(
    @SerializedName("first")
    val first: String?,

    @SerializedName("last")
    val last: String?
)
