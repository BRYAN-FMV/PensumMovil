package com.devproy.pensummovil.Pantallas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.AprobadoViewModel
import com.devproy.pensummovil.ui.theme.VerdeUnicah

@Composable
fun HistorialGrafico(navCotroller: NavController, viewModel: AprobadoViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { MyTopAppBar(title = "Historial grafico") }
    ) { innerpadding->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            LazyRow(
                Modifier.fillMaxWidth()
            ) {
                items(5) {
                    OutlinedCard(
                        Modifier
                            .wrapContentWidth()
                            .height(100.dp)
                            .padding(15.dp)
                            .clickable { showDialog = true },
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                        border = if (viewModel.isChecked.value) {
                            BorderStroke(2.dp, color = VerdeUnicah)
                        } else {
                            BorderStroke(1.dp, Color.Gray)
                        }
                    ) {
                        Column() {
                            Text("INTRODUCCIÓN",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(15.dp)
                            )
                            if(viewModel.isChecked.value){
                                Text("APROBADO",
                                    fontSize = 8.sp,
                                    color = VerdeUnicah,
                                    modifier = Modifier
                                        .padding(start = 15.dp)
                                )
                            }else{
                                Text("PENDIENTE",
                                    fontSize = 8.sp,
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .padding(start = 15.dp)
                                )
                            }
                        }

                    }
                }
            }
            HorizontalDivider(thickness = 1.dp)
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Detalles de la Clase") },
                text = {
                    Column {
                        Text("Seleccionar si la clase esta aprobada")
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = viewModel.isChecked.value,
                                onCheckedChange = { viewModel.setChecked(it) }
                            )
                            Text("Aprobada")
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }

}