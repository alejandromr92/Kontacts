package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class NameApiModel(
    @SerializedName("first")
    val first: String? = null,

    @SerializedName("last")
    val last: String? = null
)
