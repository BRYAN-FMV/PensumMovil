package com.devproy.pensummovil.Navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.devproy.pensummovil.Pantallas.*

@Composable
fun AppNavigation(navController: NavHostController){
    val navController = rememberNavController()

    Scaffold (
        modifier = Modifier.fillMaxHeight().fillMaxSize()
    ){ innerPadding ->
        Box (modifier = Modifier.fillMaxHeight().padding(innerPadding)){
            NavHost(navController, startDestination = "Login"){
                composable("Login"){
                    Login(navController)
                }
                composable("historial_grafico"){
                    HistorialGrafico()
                }
                composable("listado_clases"){
                    ListadoClases()
                }
                composable("menu"){
                    MenuPrincipal(navController)
                }
            }
        }

    }
}



