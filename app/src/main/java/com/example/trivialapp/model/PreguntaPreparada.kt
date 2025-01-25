package com.example.trivialapp.model

data class PreguntaPreparada (
    private val pregunta: String,
    private val opciones: List<String>,
    private val respuestaCorrecta: String
) {

}

