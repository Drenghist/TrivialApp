package com.example.trivialapp.network

import com.example.trivialapp.model.Pregunta
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PreguntasApiService {
    @GET("api.php?amount=10")
    suspend fun getApiPreguntas(): Response<PreguntasApiResponse>
}

/*
interface PreguntasApiService{
    @GET("api.php")
    suspend fun getApiPreguntas(
        @Query("amount") amount: Int = 10,
        @Query("type") type: String = "multiple"
    ): Response<PreguntasApiResponse>
}*/


@Serializable
data class PreguntasApiResponse(
    val response_code: Int,
    val results: List<Pregunta>
)