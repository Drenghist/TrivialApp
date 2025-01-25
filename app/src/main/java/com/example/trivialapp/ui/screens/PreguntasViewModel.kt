package com.example.trivialapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface GameUiState {
    data object Loading : GameUiState
    data object Error: GameUiState
    data class Success(val preguntas: List<Pregunta>) : GameUiState

}



class PreguntasViewModel(private val preguntasRepositorio: PreguntasRepositorio): ViewModel() {
    var gameUiState : GameUiState by mutableStateOf(GameUiState.Loading)
        private set

    init {
        getPreguntas()
    }

    fun getPreguntas() {
        viewModelScope.launch {
            gameUiState = GameUiState.Loading
            gameUiState = try {
                GameUiState.Success(preguntasRepositorio.getPreguntas())
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
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