package com.example.trivialapp.data

import com.example.trivialapp.network.PreguntasApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val preguntasRepositorio: PreguntasRepositorio
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://opentdb.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: PreguntasApiService by lazy {
        retrofit.create(PreguntasApiService::class.java)
    }

    override val preguntasRepositorio: PreguntasRepositorio by lazy {
        NetworkPreguntasRepositorio(retrofitService)
    }
}