package com.alejandromr.kontacts.domain

data class ContactModel(
    val name: NameModel,
    val gender: String,
    val location: LocationModel,
    val registered: RegistrationModel,
    val phone: String,
    val email: String,
    val picture: PictureModel
)


