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

@Composable
fun ListadoClases(
    claseViewModel: ClaseViewModel = viewModel(),
    listadoClaseViewModel: ListadoClaseViewModel = viewModel()
){
    //varible de clase
    val claseState by claseViewModel.claseData.collectAsState()
    //variable de Alumno
    val alumnoState by listadoClaseViewModel.alumnoData.collectAsState()
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        claseViewModel.obtenerClaseData()
        listadoClaseViewModel.obtenerAlumnoData("")

    }

    Scaffold(
        topBar = { MyTopAppBar(title = "Historial gráfico") }
    ) { innerPadding ->
        Column(Modifier
            .padding(innerPadding)
        ){
              OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Ingrese ID del Alumno") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    if (searchText.isNotEmpty()) {
                        listadoClaseViewModel.obtenerAlumnoData(searchText)
                    }
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar")
                }
            }
        )
               alumnoState?.let { alumno ->
                   Row(){
                       Text(text = "Alumno: ${alumno.nombre}", color = Color.Black)
                       Spacer(modifier = Modifier.width(25.dp))

                       Text(text = "ID: ${alumno.alumnoId}", color = Color.Black)
                   }
                   Column() {
                      Text(" Aprobado ${alumno.}")
               }

               claseState?.let { resultant ->
                   LazyColumn(
                       Modifier
                           .fillMaxSize()
                           .padding(innerPadding),
                   ) {
                       item{
                           Card(Modifier
                               .fillMaxWidth()
                               .padding(15.dp)
                               .height(75.dp)) {
                               Text("esto en un card")

                           }
                       }
                   }
               }
           }
            alumnoState?.let { alumno ->
                    Row(){
                        Text(text = "Alumno: ${alumno.nombre}", color = Color.Black)
                        Spacer(modifier = Modifier.width(25.dp))

                        Text(text = "ID: ${alumno.alumnoId}", color = Color.Black)
                    }
            }

            claseState?.let { resultant ->
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    item{
                        Card(Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                            .height(75.dp)) {
                            Text("esto en un card")

                        }
                    }
                    items(resultant.result) { clase ->
                        Text(text = "Clase: ${clase.nombre_clase} ", color = Color.Black)
                        Text("ID : ${clase.id_clase}")
                    }
                }
            }
        }
    }


