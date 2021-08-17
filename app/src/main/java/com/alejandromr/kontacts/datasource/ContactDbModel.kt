package com.alejandromr.kontacts.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
data class ContactDbModel(
    val name: String,
    val surname: String,
    val gender: String,
    val streetName: String,
    val streetNumber: String,
    val city: String,
    val state: String,
    val registeredDate: String,
    val phone: String,
    @PrimaryKey val email: String,
    val pictureThumbnail: String,
    val pictureMedium: String,
    val pictureLarge: String,
    var deleted: Boolean = false
): Serializable




