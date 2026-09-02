package com.example.ecoimpact.model

import kotlinx.serialization.Serializable

@Serializable
data class AirQualityResponse(
    val current: AirQualityCurrent
)

@Serializable
data class AirQualityCurrent(
    val pm10: Double,
    val pm2_5: Double,
    val european_aqi: Int,
    val carbon_monoxide: Double = 0.0,
    val nitrogen_dioxide: Double = 0.0,
    val sulphur_dioxide: Double = 0.0,
    val ozone: Double = 0.0
)

data class AirQualityInfo(
    val aqi: Int,
    val pm25: Double,
    val pm10: Double,
    val co: Double,
    val no2: Double,
    val so2: Double,
    val o3: Double,
    val classification: String,
    val level: AirQualityLevel
)

enum class AirQualityLevel {
    GOOD, FAIR, MODERATE, POOR, VERY_POOR, EXTREMELY_POOR
}
