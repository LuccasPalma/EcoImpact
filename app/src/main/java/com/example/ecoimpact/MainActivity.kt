package com.example.ecoimpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoimpact.ui.theme.EcoImpactTheme
import kotlinx.coroutines.delay
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button

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

    var mostrarSplash by remember { mutableStateOf(true) }
    var telaAtual by remember { mutableStateOf("home") }

    LaunchedEffect(Unit) {
        delay(2000)
        mostrarSplash = false
    }

    if (mostrarSplash) {
        TelaSplash()
    } else {
        if (telaAtual == "home") {
            TelaHome(
                onAbrirQualidadeDoAr = {
                    telaAtual = "qualidade"
                }
            )
        } else {
            TelaQualidadeDoAr(
                onVoltar = {
                    telaAtual = "home"
                }
            )
        }
    }
}
@Composable
fun TelaSplash() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "🌱",
            fontSize = 60.sp
        )

        Text(
            text = "EcoImpact",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Impacto ambiental na palma da sua mão",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun TelaHome(
    onAbrirQualidadeDoAr: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 90.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🌱 EcoImpact",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Qualidade ambiental da sua região"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAbrirQualidadeDoAr()
                }
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "Qualidade do ar",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "📍 São Paulo"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "PM2.5"
                )

                Text(
                    text = "20,4 µg/m³",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "PM10"
                )

                Text(
                    text = "20,9 µg/m³",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "🟢 Qualidade: Boa",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "🌱 Impacto ambiental",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Acompanhe a qualidade do ar e descubra como suas escolhas podem contribuir para um ambiente melhor."
                )
            }
        }
    }
}
    @Composable
    fun TelaQualidadeDoAr(
        onVoltar: () -> Unit
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(90.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "🌬️ Qualidade do ar",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "📍 São Paulo"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "PM2.5"
                    )

                    Text(
                        text = "20,4 µg/m³",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "PM10"
                    )

                    Text(
                        text = "20,9 µg/m³",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "🟢 Qualidade: Boa",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onVoltar
            ) {
                Text("← Voltar")
            }
        }
    }