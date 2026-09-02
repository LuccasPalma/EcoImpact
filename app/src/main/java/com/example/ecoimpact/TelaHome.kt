package com.example.ecoimpact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoimpact.model.TransportType
import com.example.ecoimpact.model.brazilianCities

@Composable
fun TelaHome(
    onAbrirQualidadeDoAr: (Int) -> Unit,
    onAbrirCalculadora: (Int, String) -> Unit,
    onAbrirImpacto: () -> Unit
) {
    var cidadeSelecionadaIndex by remember { mutableStateOf(0) }
    var expandirCidade by remember { mutableStateOf(false) }

    var transporteSelecionadoIndex by remember { mutableStateOf(0) }
    var expandirTransporte by remember { mutableStateOf(false) }

    var distanciaInput by remember { mutableStateOf("") }
    var erroDistancia by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFBFBFB))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // HEADER
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.Eco,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "EcoImpact",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )
            Text(
                text = "Qualidade ambiental da sua região",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // CARD 1 - QUALIDADE DO AR
        HomeCard {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Air,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Qualidade do ar",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Selecione sua cidade", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))

                BoxDropdown(
                    text = brazilianCities[cidadeSelecionadaIndex].displayName,
                    icon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    onClick = { expandirCidade = true }
                )

                DropdownMenu(
                    expanded = expandirCidade,
                    onDismissRequest = { expandirCidade = false },
                    modifier = Modifier.fillMaxWidth(0.85f).background(Color.White)
                ) {
                    brazilianCities.forEachIndexed { index, city ->
                        DropdownMenuItem(
                            text = { Text(city.displayName) },
                            onClick = {
                                cidadeSelecionadaIndex = index
                                expandirCidade = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                EcoButton(
                    text = "Mostrar Resultado",
                    onClick = { onAbrirQualidadeDoAr(cidadeSelecionadaIndex) }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CARD 2 - CALCULADORA
        HomeCard {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DirectionsCar,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Calculadora",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Selecione o meio de transporte", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))

                BoxDropdown(
                    text = TransportType.entries[transporteSelecionadoIndex].displayName,
                    icon = { Text(TransportType.entries[transporteSelecionadoIndex].icon, fontSize = 20.sp) },
                    onClick = { expandirTransporte = true }
                )

                DropdownMenu(
                    expanded = expandirTransporte,
                    onDismissRequest = { expandirTransporte = false },
                    modifier = Modifier.fillMaxWidth(0.85f).background(Color.White)
                ) {
                    TransportType.entries.forEachIndexed { index, transport ->
                        DropdownMenuItem(
                            text = { Text("${transport.icon} ${transport.displayName}") },
                            onClick = {
                                transporteSelecionadoIndex = index
                                expandirTransporte = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Distância em km", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = distanciaInput,
                    onValueChange = {
                        distanciaInput = it
                        erroDistancia = false
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Ex.: 25", color = Color.LightGray) },
                    leadingIcon = { Icon(Icons.Default.Straighten, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
                    trailingIcon = { Text("km", modifier = Modifier.padding(end = 12.dp), color = Color.Gray) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = erroDistancia,
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.5f)
                    )
                )
                if (erroDistancia) {
                    Text(
                        text = "Informe uma distância válida.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                EcoButton(
                    text = "Mostrar Resultado",
                    onClick = {
                        val dist = distanciaInput.toDoubleOrNull()
                        if (dist != null && dist >= 0) {
                            onAbrirCalculadora(transporteSelecionadoIndex, distanciaInput)
                        } else {
                            erroDistancia = true
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // CARD 3 - IMPACTO AMBIENTAL
        HomeCard {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Eco,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Impacto Ambiental",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Acompanhe a qualidade do ar e descubra como suas escolhas podem contribuir para um ambiente melhor.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onAbrirImpacto,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE8F5E9),
                        contentColor = Color(0xFF2E7D32)
                    ),
                    elevation = null
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Saiba Mais", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun HomeCard(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF0F0F0))
    ) {
        content()
    }
}

@Composable
fun EcoButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun BoxDropdown(
    text: String,
    icon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                icon()
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(text = text, modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.Gray)
        }
    }
}
