package com.example.ecoimpact.model

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val name: String,
    val state: String,
    val latitude: Double,
    val longitude: Double
) {
    val displayName: String get() = "$name - $state"
}

val brazilianCities = listOf(
    City("São Paulo", "SP", -23.5505, -46.6333),
    City("Rio de Janeiro", "RJ", -22.9068, -43.1729),
    City("Belo Horizonte", "MG", -19.9167, -43.9345),
    City("Curitiba", "PR", -25.4284, -49.2733),
    City("Brasília", "DF", -15.7939, -47.8828),
    City("Salvador", "BA", -12.9777, -38.5016)
)
