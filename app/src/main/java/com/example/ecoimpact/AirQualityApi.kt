package com.example.ecoimpact

import com.example.ecoimpact.model.AirQualityInfo
import com.example.ecoimpact.model.AirQualityLevel
import com.example.ecoimpact.model.AirQualityResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL

object AirQualityApi {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun buscarQualidadeDoAr(latitude: Double, longitude: Double): AirQualityInfo? {
        return withContext(Dispatchers.IO) {
            try {
                val urlString = "https://air-quality-api.open-meteo.com/v1/air-quality" +
                        "?latitude=$latitude" +
                        "&longitude=$longitude" +
                        "&current=pm10,pm2_5,european_aqi,carbon_monoxide,nitrogen_dioxide,sulphur_dioxide,ozone" +
                        "&timezone=America%2FSao_Paulo"

                val url = URL(urlString)
                val conexao = url.openConnection() as HttpURLConnection
                conexao.requestMethod = "GET"
                conexao.connectTimeout = 5000
                conexao.readTimeout = 5000

                if (conexao.responseCode == 200) {
                    val resposta = conexao.inputStream.bufferedReader().use { it.readText() }
                    val apiResponse = json.decodeFromString<AirQualityResponse>(resposta)
                    val current = apiResponse.current

                    AirQualityInfo(
                        aqi = current.european_aqi,
                        pm25 = current.pm2_5,
                        pm10 = current.pm10,
                        co = current.carbon_monoxide,
                        no2 = current.nitrogen_dioxide,
                        so2 = current.sulphur_dioxide,
                        o3 = current.ozone,
                        classification = getClassification(current.european_aqi),
                        level = getLevel(current.european_aqi)
                    )
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun getClassification(aqi: Int): String {
        return when (aqi) {
            in 0..20 -> "Boa"
            in 21..40 -> "Regular"
            in 41..60 -> "Moderada"
            in 61..80 -> "Ruim"
            in 81..100 -> "Muito Ruim"
            else -> "Extremamente Ruim"
        }
    }

    private fun getLevel(aqi: Int): AirQualityLevel {
        return when (aqi) {
            in 0..20 -> AirQualityLevel.GOOD
            in 21..40 -> AirQualityLevel.FAIR
            in 41..60 -> AirQualityLevel.MODERATE
            in 61..80 -> AirQualityLevel.POOR
            in 81..100 -> AirQualityLevel.VERY_POOR
            else -> AirQualityLevel.EXTREMELY_POOR
        }
    }
}
