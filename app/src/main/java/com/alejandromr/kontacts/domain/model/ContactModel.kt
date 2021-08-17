package com.alejandromr.kontacts.domain.model

import java.io.Serializable

data class ContactModel(
    val name: NameModel,
    val gender: String,
    val location: LocationModel,
    val registered: RegistrationModel,
    val phone: String,
    val email: String,
    val picture: PictureModel,
    var deleted: Boolean = false
) : Serializable


