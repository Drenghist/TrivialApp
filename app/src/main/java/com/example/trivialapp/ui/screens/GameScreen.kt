package com.example.trivialapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trivialapp.model.Pregunta



@Composable
fun GameScreen(
    preguntasViewModel: PreguntasViewModel = viewModel(factory = PreguntasViewModel.Factory),
) {
    var preguntaTest: List<Pregunta> = listOf(Pregunta("pregunta1","resp1",listOf("op1","op2")),Pregunta("preg2","resp2",listOf("op3","op4")))
    Column(modifier = Modifier
        .padding(12.dp)
        .padding(top = 40.dp)) {
        Text(

            preguntasViewModel.gameViewState.toString(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(20.dp).fillMaxWidth())
        Column(modifier = Modifier
            .padding(12.dp)
            .padding(top = 40.dp)) {
            Text(
                preguntaTest.toString(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}