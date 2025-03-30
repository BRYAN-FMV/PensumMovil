package com.devproy.pensummovil.Pantallas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.*
import com.devproy.pensummovil.ui.theme.VerdeUnicah

@Composable
fun HistorialGrafico(
    navController: NavController,
    aprobadoViewModel: AprobadoViewModel = viewModel(),
    claseViewModel: ClaseViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val clases by claseViewModel.claseData.collectAsState()

    LaunchedEffect(Unit) {
        claseViewModel.obtenerClaseData()
    }

    Scaffold(
        topBar = { MyTopAppBar(title = "Historial gráfico") }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyRow(
                Modifier.fillMaxWidth()
            ) {
                item {
                    Box(
                        Modifier.height(100.dp)
                    ) {
                        Text(
                            "I", fontSize = 35.sp,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .align(Alignment.Center)
                        )
                    }
                }

                items(2) {
                    OutlinedCard(
                        Modifier
                            .wrapContentWidth()
                            .height(100.dp)
                            .padding(15.dp)
                            .clickable { showDialog = true },
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        border = if (aprobadoViewModel.isChecked.value) {
                            BorderStroke(2.dp, VerdeUnicah)
                        } else {
                            BorderStroke(1.dp, Color.Gray)
                        }
                    ) {
                        Column {
                            Text(
                                text = "clase",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(15.dp)
                            )
                            Text(
                                text = if (aprobadoViewModel.isChecked.value) "APROBADO" else "PENDIENTE",
                                fontSize = 8.sp,
                                color = if (aprobadoViewModel.isChecked.value) VerdeUnicah else Color.Gray,
                                modifier = Modifier.padding(start = 15.dp)
                            )
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
                        Text("Seleccionar si la clase está aprobada")
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Switch(
                                checked = aprobadoViewModel.isChecked.value,
                                onCheckedChange = { aprobadoViewModel.setChecked(it) },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = VerdeUnicah,
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color.Gray,
                                    uncheckedBorderColor = Color.Transparent
                                )
                            )
                            Text(
                                text = if (aprobadoViewModel.isChecked.value) "Aprobada" else "Pendiente",
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        HorizontalDivider(thickness = 1.dp)
                        Text("REQUISITO:", fontSize = 15.sp, modifier = Modifier.padding(top = 5.dp))
                        Text("clase requisito", modifier = Modifier.padding(top = 5.dp))
                        HorizontalDivider(thickness = 1.dp)
                        Text("ES REQUISITO DE:", fontSize = 15.sp, modifier = Modifier.padding(top = 5.dp))
                        Text("es requisito", modifier = Modifier.padding(top = 5.dp))
                        HorizontalDivider(thickness = 1.dp)
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Listo", color = Color.DarkGray)
                    }
                }
            )
        }
    }
}
