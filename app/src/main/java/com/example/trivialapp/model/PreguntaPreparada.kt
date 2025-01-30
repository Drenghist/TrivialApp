package com.example.trivialapp.model

data class PreguntaPreparada (
    private val rawPregunta: String,
    private val rawOpciones: List<String>,
    private val rawRespuestaCorrecta: String
) {
    val question: String
        get() = rawPregunta.decodeHtml()

    val options: List<String>
        get() = rawOpciones.map { it.decodeHtml() }

    val correctAnswer: String
        get() = rawRespuestaCorrecta.decodeHtml()

    fun validateAnswer(answer: String): Boolean {
        return answer == correctAnswer
    }
}



private fun String.decodeHtml(): String {
    return android.text.Html.fromHtml(this, android.text.Html.FROM_HTML_MODE_LEGACY).toString()
}