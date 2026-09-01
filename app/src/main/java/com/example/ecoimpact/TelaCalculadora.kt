package com.example.ecoimpact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TelaCalculadora(
    onVoltar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🚗 Calculadora",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Calcule uma estimativa do seu impacto"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "Selecione o meio de transporte",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "🚗 Carro (Gasolina)"
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Distância percorrida"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "25 km"
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // O cálculo será implementado depois
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Mostrar Resultado")
                }
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
                    text = "🌱 Resultado",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Seu impacto estimado aparecerá aqui."
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