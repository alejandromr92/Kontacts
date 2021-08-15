package com.alejandromr.kontacts.domain

import com.google.gson.annotations.SerializedName

data class PictureModel(
    val large: String,
    val medium: String,
    val thumbnail: String
)
