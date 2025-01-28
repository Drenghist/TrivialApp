package com.example.trivialapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.trivialapp.TrivialApplication
import com.example.trivialapp.data.PreguntasRepositorio
import com.example.trivialapp.model.Pregunta
import com.example.trivialapp.model.PreguntaPreparada
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface GameUiState {
    data object Loading : GameUiState
    data object Error: GameUiState
    data class Success(val preguntas: List<Pregunta>) : GameUiState
    data object Home : GameUiState
}

data class GameViewState(
    val uiState: GameUiState = GameUiState.Loading,
    //val questions: List<PreguntaPreparada> = emptyList(), --- Lo anulo para pruebas
    val questions: List<Pregunta> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val correctAnswers: Int = 0,
    val numberOfQuestions: Int = 5,
    val questionReplied: Boolean = false,
    val currentQuestion: PreguntaPreparada? = null,
    val currentPercentage: Int = 0,
    val actualRecord: Int = 0,
    val gameFinished: Boolean = false,
    val newRecord: Boolean = false,
)



class PreguntasViewModel(private val preguntasRepositorio: PreguntasRepositorio): ViewModel() {
    private val _gameViewState = MutableStateFlow(GameViewState())
    val gameViewState : StateFlow<GameViewState> = _gameViewState.asStateFlow()
    //variable temporal


    init {
        _gameViewState.value = _gameViewState.value.copy(uiState = GameUiState.Home)
        getPreguntas()
    }

    /* Función getPreguntas sencilla. No funciona

    var preguntaTemp: List<Pregunta> = listOf()
    fun getPreguntas(): List<Pregunta> {
        viewModelScope.launch {
            _gameViewState.value = _gameViewState.value.copy(uiState = GameUiState.Loading)
            preguntaTemp = preguntasRepositorio.getPreguntas()
        }
        return preguntaTemp
    }*/

    fun getPreguntas() {
        viewModelScope.launch {
            //_gameViewState.value = _gameViewState.value.copy(uiState = GameUiState.Loading)
            Log.d("antes del launch",preguntasRepositorio.getPreguntas().toString())
            _gameViewState.value = _gameViewState.value.copy(questions = preguntasRepositorio.getPreguntas())
            Log.d("despues del launch",preguntasRepositorio.getPreguntas().toString())
        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TrivialApplication)
                val preguntasRepositorio = application.container.preguntasRepositorio
                PreguntasViewModel (preguntasRepositorio = preguntasRepositorio)
            }
        }
    }


}