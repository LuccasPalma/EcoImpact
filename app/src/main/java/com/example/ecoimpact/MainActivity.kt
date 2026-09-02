package com.example.ecoimpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecoimpact.ui.theme.EcoImpactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoImpactTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            TelaSplash(
                onFinish = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            TelaHome(
                onAbrirQualidadeDoAr = { cityIndex ->
                    navController.navigate("qualidade/$cityIndex")
                },
                onAbrirCalculadora = { transportIndex, distancia ->
                    navController.navigate("calculadora/$transportIndex/$distancia")
                },
                onAbrirImpacto = {
                    navController.navigate("impacto")
                }
            )
        }

        composable(
            route = "qualidade/{cityIndex}",
            arguments = listOf(navArgument("cityIndex") { type = NavType.IntType })
        ) { backStackEntry ->
            val cityIndex = backStackEntry.arguments?.getInt("cityIndex") ?: 0
            TelaQualidadeDoAr(
                cityIndex = cityIndex,
                onVoltar = { navController.popBackStack() }
            )
        }

        composable(
            route = "calculadora/{transportIndex}/{distancia}",
            arguments = listOf(
                navArgument("transportIndex") { type = NavType.IntType },
                navArgument("distancia") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val transportIndex = backStackEntry.arguments?.getInt("transportIndex") ?: 0
            val distancia = backStackEntry.arguments?.getString("distancia") ?: "0"
            TelaCalculadora(
                transportIndex = transportIndex,
                distanciaInput = distancia,
                onVoltar = { navController.popBackStack() }
            )
        }

        composable("impacto") {
            TelaImpacto(
                onVoltar = { navController.popBackStack() }
            )
        }
    }
}
