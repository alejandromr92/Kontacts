package com.alejandromr.kontacts.domain.model

import java.io.Serializable

data class PictureModel(
    val large: String,
    val medium: String,
    val thumbnail: String
) : Serializable
