package com.example.ecoimpact

import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun buscarQualidadeDoAr(): String {

    return withContext(Dispatchers.IO) {

        val url = URL(
            "https://air-quality-api.open-meteo.com/v1/air-quality" +
                    "?latitude=-23.5475" +
                    "&longitude=-46.6361" +
                    "&current=pm10,pm2_5,european_aqi" +
                    "&timezone=America%2FSao_Paulo"
        )

        val conexao = url.openConnection() as HttpURLConnection

        conexao.requestMethod = "GET"

        val resposta = conexao.inputStream
            .bufferedReader()
            .use { it.readText() }

        conexao.disconnect()

        resposta
    }
}