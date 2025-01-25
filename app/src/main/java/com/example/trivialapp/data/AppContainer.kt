package com.example.trivialapp.data

import com.example.trivialapp.network.PreguntasApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val preguntasRepositorio: PreguntasRepositorio
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://opentdb.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: PreguntasApiService by lazy {
        retrofit.create(PreguntasApiService::class.java)
    }

    override val preguntasRepositorio: PreguntasRepositorio by lazy {
        NetworkPreguntasRepositorio(retrofitService)
    }
}