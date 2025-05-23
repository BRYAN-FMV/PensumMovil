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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.devproy.pensummovil.ui.theme.AzulUnicah

@Composable
fun ListadoClases(
    listadoClaseViewModel: ListadoClaseViewModel = viewModel(),
    navController: NavController,
    userId: String
) {
    // Variables de estado

    val alumnoState by listadoClaseViewModel.alumnoData.collectAsState()
    val aprobadoState by listadoClaseViewModel.aprobadoData.collectAsState()  // Estado para clases aprobadas
    var searchText by remember { mutableStateOf("") }
    val claseData by listadoClaseViewModel.claseData.collectAsState()
    var claseNombre by remember { mutableStateOf<Clase?>(null) }

    // Lógica de carga inicial
    LaunchedEffect(Unit) {
        listadoClaseViewModel.obtenerAlumnoData("") // Cargar alumno con ID vacío por defecto
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
            // Campo de búsqueda por ID de alumno
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Ingrese ID del Alumno") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulUnicah
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        if (searchText.isNotEmpty()) {
                            listadoClaseViewModel.obtenerAlumnoData(searchText) // Obtener alumno por ID
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            )

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




