package com.devproy.pensummovil.Navigation

import LoginViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.devproy.pensummovil.Pantallas.*
import com.devproy.pensummovil.ViewModel.*
import com.devproy.pensummovil.ViewModel.*
import kotlin.math.log

@Composable
fun AppNavigation(navHostController: NavHostController) {
    val navController = rememberNavController()
    val loginViewModel: LoginViewModel = viewModel()
    val historialGraficoViewModel: HistorialGaficoViewModel = viewModel()
    val listadoClaseViewModel: ListadoClaseViewModel = viewModel()

    Scaffold(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxHeight().padding(innerPadding)) {
            NavHost(navController, startDestination = "Login") {
                // Pantalla Login
                composable("Login") {
                    Login(loginViewModel, navController)
                }

                // Pantalla Menú: Se pasa el userId desde Login
                composable("menu/{userId}/{roleId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: ""
                    val roleId = backStackEntry.arguments?.getString("roleId") ?: ""
                    MenuPrincipal(navController, userId, roleId)
                }

                // Pantalla Historial Gráfico: Se pasa el userId desde el Menú
                composable("historial_grafico/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: ""
                    HistorialGrafico(navController, historialGraficoViewModel ,userId)
                }

                composable("lista_aprobado/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: ""
                    ListaAprobado(listadoClaseViewModel, navController, userId)
                }

                // Pantalla Listado Clases: (Sin userId, según lo requerido)
                composable("listado_clases/{userId}") { backStackEntry ->
                    val userId = backStackEntry.arguments?.getString("userId") ?: ""
                    ListadoClases(listadoClaseViewModel, navController, userId)
                }

            }
        }
    }
}




