package com.example.ecoimpact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoimpact.model.AirQualityInfo
import com.example.ecoimpact.model.AirQualityLevel
import com.example.ecoimpact.model.brazilianCities

@Composable
fun TelaQualidadeDoAr(
    cityIndex: Int,
    onVoltar: () -> Unit
) {
    val city = brazilianCities[cityIndex]
    var loading by remember { mutableStateOf(true) }
    var airQualityInfo by remember { mutableStateOf<AirQualityInfo?>(null) }
    var error by remember { mutableStateOf(false) }

    LaunchedEffect(cityIndex) {
        loading = true
        error = false
        val result = AirQualityApi.buscarQualidadeDoAr(city.latitude, city.longitude)
        if (result != null) {
            airQualityInfo = result
        } else {
            error = true
        }
        loading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBFBFB))
    ) {
        // TOP BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 8.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onVoltar) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Color(0xFF1B5E20))
            }
            Text(
                text = "Qualidade do ar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(48.dp)) // Alinhamento
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = city.displayName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (loading) {
                Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else if (error || airQualityInfo == null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red, modifier = Modifier.size(48.dp))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Não foi possível consultar os dados no momento.", color = Color.Red, textAlign = TextAlign.Center)
                    }
                }
            } else {
                val info = airQualityInfo!!
                val levelColor = when (info.level) {
                    AirQualityLevel.GOOD -> Color(0xFF4CAF50)
                    AirQualityLevel.FAIR -> Color(0xFF8BC34A)
                    AirQualityLevel.MODERATE -> Color(0xFFFFC107)
                    AirQualityLevel.POOR -> Color(0xFFFF9800)
                    AirQualityLevel.VERY_POOR -> Color(0xFFF44336)
                    AirQualityLevel.EXTREMELY_POOR -> Color(0xFFB71C1C)
                }

                // AQI CARD
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF0F0F0))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = info.classification,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = levelColor,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "AQI: ${info.aqi}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        
                        if (info.level != AirQualityLevel.GOOD && info.level != AirQualityLevel.FAIR) {
                            Spacer(modifier = Modifier.height(20.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = levelColor.copy(alpha = 0.05f)),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Warning, contentDescription = null, tint = levelColor, modifier = Modifier.size(24.dp))
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = "Atenção: Os níveis de poluentes estão acima do ideal.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = levelColor,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = "Principais Poluentes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(16.dp))

                PollutantRow(Icons.Default.Air, "PM2.5", "${info.pm25} µg/m³", Icons.Default.Air, "PM10", "${info.pm10} µg/m³")
                Spacer(modifier = Modifier.height(12.dp))
                PollutantRow(Icons.Default.Air, "NO₂", "${info.no2} µg/m³", Icons.Default.Air, "O₃", "${info.o3} µg/m³")
                Spacer(modifier = Modifier.height(12.dp))
                PollutantRow(Icons.Default.Air, "CO", "${info.co} µg/m³", Icons.Default.Air, "SO₂", "${info.so2} µg/m³")

                Spacer(modifier = Modifier.height(32.dp))


                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF0F0F0))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF1B5E20))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "O que isso significa?", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        ExplanationItem("PM2.5 / PM10", "Partículas finas inaláveis presentes no ar.")
                        ExplanationItem("NO₂ (Dióxido de Nitrogênio)", "Poluente comum de veículos e indústrias.")
                        ExplanationItem("O₃ (Ozônio)", "Gás que em excesso na superfície é prejudicial.")
                        ExplanationItem("CO (Monóxido de Carbono)", "Gás incolor resultante de combustão.")
                        ExplanationItem("SO₂ (Dióxido de Enxofre)", "Resultante da queima de combustíveis fósseis.")
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = onVoltar,
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF0F0F0),
                    contentColor = Color.DarkGray
                )
            ) {
                Text("Voltar", fontWeight = FontWeight.Bold)
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun PollutantRow(icon1: androidx.compose.ui.graphics.vector.ImageVector, name1: String, value1: String, icon2: androidx.compose.ui.graphics.vector.ImageVector, name2: String, value2: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        PollutantCard(icon1, name1, value1, modifier = Modifier.weight(1f))
        PollutantCard(icon2, name2, value2, modifier = Modifier.weight(1f))
    }
}

@Composable
fun PollutantCard(icon: androidx.compose.ui.graphics.vector.ImageVector, name: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Color(0xFF4CAF50), modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = name, style = MaterialTheme.typography.bodySmall, color = Color.Gray, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

@Composable
fun ExplanationItem(title: String, description: String) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(text = title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall, color = Color(0xFF2E7D32))
        Text(text = description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}
