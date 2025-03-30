package com.devproy.pensummovil.Pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.*
import com.devproy.pensummovil.Model.*

@Composable
fun ListadoClases(
    claseViewModel: ClaseViewModel = viewModel()
){
    //varible de clase
    val claseState by claseViewModel.claseData.collectAsState()

    LaunchedEffect(Unit) {
        claseViewModel.obtenerClaseData()
    }

    Scaffold(
        topBar = { MyTopAppBar(title = "Historial gráfico") }
    ) { innerPadding ->
        claseState?.result?.let { listaClases ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(listaClases) { clase ->
                    Text(text = "Clase: ${clase.nombre_clase}")
                }
            }
        } ?: run {
            LazyColumn {
                item {
                    Text("No hay clases disponibles")
                }
            }
        }
    }
}
