package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class PictureApiModel(
    @SerializedName("large")
    val large: String?,

    @SerializedName("medium")
    val medium: String?,

    @SerializedName("thumbnail")
    val thumbnail: String?
)
