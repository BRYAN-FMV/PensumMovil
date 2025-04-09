package com.devproy.pensummovil.Pantallas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devproy.pensummovil.Model.Clase
import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.ListadoClaseViewModel

@Composable
fun ListaAprobado(
    listadoClaseViewModel: ListadoClaseViewModel = viewModel(),
    navController: NavController,
    userId: String
) {
    // Variables de estado

    val alumnoState by listadoClaseViewModel.alumnoData.collectAsState()
    val aprobadoState by listadoClaseViewModel.aprobadoData.collectAsState()
    val claseData by listadoClaseViewModel.claseData.collectAsState()
    var claseNombre by remember { mutableStateOf<Clase?>(null) }

    // Lógica de carga inicial
    LaunchedEffect(userId) {
        listadoClaseViewModel.obtenerAlumnoData(userId)
        listadoClaseViewModel.obtenerClasesAprobadas(userId)
    }

    // Estructura de la pantalla
    Scaffold(
        topBar = { MyTopAppBar(title = "Clases aprobadas",navController) }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Mostrar la información del alumno
            alumnoState?.let { alumno ->
                OutlinedCard (Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                ) {
                    Row(Modifier
                        .padding(10.dp)
                    ){
                        Text(text = "Alumno: ${alumno.nombre}",
                            color = Color.Black,
                            modifier = Modifier
                                .padding(10.dp))
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(text = "ID: ${alumno.alumnoId}",
                            color = Color.Black,
                            modifier = Modifier
                                .padding(10.dp))
                    }

                }

                // Mostrar las clases aprobadas
                aprobadoState?.let { aprobado ->
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Clases Aprobadas", fontSize = 20.sp)

                        if (aprobado.result.isNotEmpty()) {
                            // Lista de clases aprobadas
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(aprobado.result) { clase ->
                                    Row(Modifier.padding(10.dp)){
                                        claseNombre = claseData.find { it.id_clase == clase.id_clase }
                                        Text(text = clase.id_clase)
                                        Spacer(Modifier.width(30.dp))
                                        VerticalDivider(thickness = 1.dp)
                                        Text(claseNombre?.nombre_clase.toString())
                                    }


                                    HorizontalDivider(thickness = 1.dp)
                                }
                            }
                        } else {
                            Text(text = "No tiene clases aprobadas.", color = Color.Gray)
                        }
                    }
                }?: Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Cargando datos...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}