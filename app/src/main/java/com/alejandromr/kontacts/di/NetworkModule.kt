package com.alejandromr.kontacts.di

import com.alejandromr.kontacts.networkclient.NetworkClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { OkHttpClient() }
    single<Gson> { GsonBuilder().create() }
    single { NetworkClient(get(), get()) }
}
