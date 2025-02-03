package com.example.trivialapp.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun GameScreen(
    preguntasViewModel: PreguntasViewModel = viewModel(factory = PreguntasViewModel.Factory),
) {
    val gameViewState by preguntasViewModel.gameViewState.collectAsState()

    Column(modifier = Modifier
        .padding(12.dp)
        .padding(top = 40.dp)) {

        when (gameViewState.uiState) {
            GameUiState.Home -> {
                //Aquí va lo que se ejecuta cuando abrimos
                Button(
                    onClick = {
                        preguntasViewModel.getPreguntas()
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Empezar juego")
                }
            }
            GameUiState.Loading -> {
                // Loading state
                Text("Loading...")
            }
            is GameUiState.Error -> {
                // Error state
                Text("Error: ${(gameViewState.uiState as GameUiState.Error).message}")
            }
            GameUiState.Success -> {
                // Game state
                Text(text = gameViewState.currentQuestion?.question ?: "No se encontró")
            }
        }
    }
}
