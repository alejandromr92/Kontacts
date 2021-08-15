package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class RegistrationApiModel(
    @SerializedName("date")
    val date: String?
)
