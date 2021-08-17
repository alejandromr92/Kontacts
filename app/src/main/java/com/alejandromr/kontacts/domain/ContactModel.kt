package com.alejandromr.kontacts.domain

import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import java.io.Serializable

data class ContactModel(
    val name: NameModel,
    val gender: String,
    val location: LocationModel,
    val registered: RegistrationModel,
    val phone: String,
    val email: String,
    val picture: PictureModel
) : Serializable


