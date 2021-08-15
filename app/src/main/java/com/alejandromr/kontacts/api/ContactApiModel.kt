package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class ContactApiModel(
    @SerializedName("name")
    val name: NameApiModel?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("location")
    val location: LocationApiModel?,

    @SerializedName("registered")
    val registered: RegistrationApiModel?,

    @SerializedName("phone")
    val phone: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("picture")
    val picture: PictureApiModel?
)

