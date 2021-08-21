package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class PictureApiModel(
    @SerializedName("large")
    val large: String? = null,

    @SerializedName("medium")
    val medium: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null
)
