package com.example.ecoimpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcoImpactTheme {
                TelaInicial()
            }
        }
    }
}

@Composable
fun TelaInicial() {

    var mostrarSplash by remember {
        mutableStateOf(true)
    }

    var telaAtual by remember {
        mutableStateOf("home")
    }

    LaunchedEffect(Unit) {
        delay(2000)
        mostrarSplash = false
    }

    if (mostrarSplash) {

        TelaSplash()

    } else {

        when (telaAtual) {

            "home" -> {
                TelaHome(
                    onAbrirQualidadeDoAr = {
                        telaAtual = "qualidade"
                    },

                    onAbrirCalculadora = {
                        telaAtual = "calculadora"
                    },

                    onAbrirImpacto = {
                        telaAtual = "impacto"
                    }
                )
            }

            "qualidade" -> {
                TelaQualidadeDoAr(
                    onVoltar = {
                        telaAtual = "home"
                    }
                )
            }

            "calculadora" -> {
                TelaCalculadora(
                    onVoltar = {
                        telaAtual = "home"
                    }
                )
            }

            "impacto" -> {
                TelaImpacto(
                    onVoltar = {
                        telaAtual = "home"
                    }
                )
            }
        }
    }
}