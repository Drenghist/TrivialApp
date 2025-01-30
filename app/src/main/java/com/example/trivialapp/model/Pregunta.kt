package com.example.trivialapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pregunta(
    @SerialName("question")
    val pregunta: String,
    @SerialName("correct_answer")
    val respuestaCorrecta: String,
    @SerialName("incorrect_answers")
    val respuestasIncorrectas: List<String>
) {

    fun aPregunta(): PreguntaPreparada {
        val options = respuestasIncorrectas.toMutableList()
        options.add(respuestaCorrecta)
        return PreguntaPreparada(rawPregunta = pregunta, rawOpciones = options.shuffled(), rawRespuestaCorrecta = respuestaCorrecta)
    }
}

