package com.alejandromr.kontacts.networkclient

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson
) {

    val client: OkHttpClient by lazy { getOkHttpBuilder().build() }

    val retrofit: Retrofit by lazy { getRetrofitBuilder().build() }

    fun getOkHttpBuilder(): OkHttpClient.Builder {
        return okHttpClient.newBuilder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }

    fun getRetrofitBuilder(client: OkHttpClient = this.client): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    companion object {
        private const val BASE_URL = "https://api.randomuser.me/"
    }
}
