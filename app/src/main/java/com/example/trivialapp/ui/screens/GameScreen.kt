package com.example.trivialapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GameScreen(
    preguntasViewModel: PreguntasViewModel = viewModel(factory = PreguntasViewModel.Factory),
) {
 Text(preguntasViewModel.getPreguntas().toString())
}