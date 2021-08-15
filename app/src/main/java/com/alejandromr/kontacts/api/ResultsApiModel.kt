package com.alejandromr.kontacts.api

import com.google.gson.annotations.SerializedName

data class ResultsApiModel(
    @SerializedName("results")
    val results: Set<ContactApiModel>?
)
