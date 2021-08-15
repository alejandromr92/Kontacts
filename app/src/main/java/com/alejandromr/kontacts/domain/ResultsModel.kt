package com.alejandromr.kontacts.domain

import com.google.gson.annotations.SerializedName

data class ResultsModel(
    val results: Set<ContactModel>
)
