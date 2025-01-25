package com.example.trivialapp.data

import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.network.PreguntasApiResponse
import com.example.trivialapp.network.PreguntasApiService

interface PreguntasRepositorio {
    suspend fun getPreguntas(): List<Pregunta>
}

class NetworkPreguntasRepositorio(private val preguntasApiService : PreguntasApiService): PreguntasRepositorio {
    override suspend fun getPreguntas(): List<Pregunta> {
        return preguntasApiService.getApiPreguntas().body()?.results ?:emptyList()
    }
}

