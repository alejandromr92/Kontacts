package com.alejandromr.kontacts.datasource

object ContactDatabaseMock {

    fun getDbContacts() = listOf(
        ContactDbModel(
            "name",
            "surname",
            "gender",
            "streetName",
            "streetNumber",
            "city",
            "state",
            "registeredDate",
            "phone",
            "email",
            "pictureThumbnail",
            "pictureMedium",
            "pictureLarge",
            false
        ), ContactDbModel(
            "name2",
            "surname",
            "gender",
            "streetName",
            "streetNumber",
            "city",
            "state",
            "registeredDate",
            "phone",
            "email2",
            "pictureThumbnail",
            "pictureMedium",
            "pictureLarge",
            false
        )
    )

    fun getDuplicatedDbContacts() = listOf(
        ContactDbModel(
            "name",
            "surname",
            "gender",
            "streetName",
            "streetNumber",
            "city",
            "state",
            "registeredDate",
            "phone",
            "email",
            "pictureThumbnail",
            "pictureMedium",
            "pictureLarge",
            false
        ), ContactDbModel(
            "name",
            "surname",
            "gender",
            "streetName",
            "streetNumber",
            "city",
            "state",
            "registeredDate",
            "phone",
            "email",
            "pictureThumbnail",
            "pictureMedium",
            "pictureLarge",
            false
        )
    )
}
