package com.example.ecoimpact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TelaHome(
    onAbrirQualidadeDoAr: () -> Unit,
    onAbrirCalculadora: () -> Unit,
    onAbrirImpacto: () -> Unit
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

        // CARD 1 - QUALIDADE DO AR
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
                    text = "🌬️ Qualidade do ar",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Selecione sua cidade"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "📍 São Paulo - SP"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Mostrar Resultado →",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CARD 2 - CALCULADORA
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAbrirCalculadora()
                }
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "🚗 Calculadora",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Selecione o meio de transporte"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "🚗 Carro (Gasolina)"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Distância em km"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ex.: 25 km"
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Mostrar Resultado →",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CARD 3 - IMPACTO AMBIENTAL
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAbrirImpacto()
                }
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "🌱 Impacto Ambiental",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Acompanhe a qualidade do ar e descubra como suas escolhas podem contribuir para um ambiente melhor."
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Saiba Mais →",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}