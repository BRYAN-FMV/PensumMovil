package com.devproy.pensummovil.Pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.*
import com.devproy.pensummovil.Model.*
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@Composable
fun ListadoClases(
    claseViewModel: ClaseViewModel = viewModel()
){
    //varible de clase
    val claseState by claseViewModel.claseData.collectAsState()

    LaunchedEffect(Unit) {
        claseViewModel.obtenerClaseData()
        println("Datos obtenidos: ${claseViewModel.claseData.value}")
    }

    Scaffold(
        topBar = { MyTopAppBar(title = "Historial gráfico") }
    ) { innerPadding ->

        claseState?.let { resultant ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                items(resultant.result) { clase ->
                    Text(text = "Clase: ${clase.nombre_clase} ", color = Color.Black)
                }
            }
        } ?: run {
        }
    }
}
