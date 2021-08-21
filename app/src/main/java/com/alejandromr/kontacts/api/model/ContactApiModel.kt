package com.alejandromr.kontacts.api.model

import com.google.gson.annotations.SerializedName

data class ContactApiModel(
    @SerializedName("name")
    val name: NameApiModel? = null,

    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("location")
    val location: LocationApiModel? = null,

    @SerializedName("registered")
    val registered: RegistrationApiModel? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("picture")
    val picture: PictureApiModel? = null
)

