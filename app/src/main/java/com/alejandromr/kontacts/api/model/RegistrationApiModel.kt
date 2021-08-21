package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class RegistrationApiModel(
    @SerializedName("date")
    val date: String? = null
)
