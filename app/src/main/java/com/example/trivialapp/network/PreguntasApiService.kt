package com.example.trivialapp.network

import com.example.trivialapp.model.Pregunta
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.GET

interface PreguntasApiService {
    @GET("api.php?amount=10")
    suspend fun getApiPreguntas(): Response<PreguntasApiResponse>
}

@Serializable
data class PreguntasApiResponse(
    val response_code: Int,
    val results: List<Pregunta>
)