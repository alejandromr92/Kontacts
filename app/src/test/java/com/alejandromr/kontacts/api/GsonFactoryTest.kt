package com.alejandromr.kontacts.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonFactoryTest {

    val gSon: Gson = GsonBuilder().create()
}
