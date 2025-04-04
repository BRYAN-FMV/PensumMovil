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
fun AppNavigation(navController: NavHostController){
    val navController = rememberNavController()
    val aprobadoViewModel: AprobadoViewModel = viewModel ()
    val claseViewModel: ClaseViewModel = viewModel ()
    val alumnoViewModel: AlumnoViewModel = viewModel()
    val loginViewModel: LoginViewModel = viewModel ()

    Scaffold (
        modifier = Modifier.fillMaxHeight().fillMaxSize()
    ){ innerPadding ->
        Box (modifier = Modifier.fillMaxHeight().padding(innerPadding)){
            NavHost(navController, startDestination = "Login"){
                composable("Login"){
                    Login(loginViewModel, navController)
                }
                composable("historial_grafico"){
                    HistorialGrafico(navController,aprobadoViewModel)
                }
                composable("listado_clases"){
                    ListadoClases(claseViewModel)
                }
                composable("menu"){
                    MenuPrincipal(navController)
                }
            }
        }

    }
}



