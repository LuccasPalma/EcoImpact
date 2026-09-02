package com.example.ecoimpact.model

enum class TransportType(
    val displayName: String,
    val factor: Double, // kg CO2 por km
    val icon: String
) {
    CARRO("Carro (Gasolina)", 0.18, "🚗"),
    MOTO("Moto", 0.09, "🏍️"),
    ONIBUS("Ônibus", 0.03, "🚌"),
    BICICLETA("Bicicleta", 0.0, "🚲"),
    CAMINHADA("Caminhada", 0.0, "🚶")
}
