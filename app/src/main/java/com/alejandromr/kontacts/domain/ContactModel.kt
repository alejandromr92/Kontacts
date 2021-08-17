package com.alejandromr.kontacts.domain

import androidx.room.Entity
import com.alejandromr.kontacts.domain.model.LocationModel
import com.alejandromr.kontacts.domain.model.NameModel
import com.alejandromr.kontacts.domain.model.PictureModel
import com.alejandromr.kontacts.domain.model.RegistrationModel
import java.io.Serializable

@Entity(tableName = "contacts")
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


