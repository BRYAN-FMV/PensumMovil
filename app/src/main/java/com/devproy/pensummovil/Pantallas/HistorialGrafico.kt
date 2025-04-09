package com.devproy.pensummovil.Pantallas

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.*
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.devproy.pensummovil.Model.Clase
import com.devproy.pensummovil.ui.theme.AmarilloUnicah
import com.devproy.pensummovil.ui.theme.VerdeUnicah

@Composable
fun HistorialGrafico(
    navController: NavController,
    historialGaficoViewModel: HistorialGaficoViewModel = viewModel(),
    userId: String
) {
    val alumnoData by historialGaficoViewModel.alumnoData.collectAsState()
    val carreraCBState by historialGaficoViewModel.carreraCBData.collectAsState()
    val claseData by historialGaficoViewModel.claseData.collectAsState()
    val clasesAprobadas by historialGaficoViewModel.clasesAprobadas.collectAsState()


    // Estado para controlar el AlertDialog y la clase seleccionada
    var showDialog by remember { mutableStateOf(false) }
    var claseSeleccionada by remember { mutableStateOf<Clase?>(null) }
    var claseNombre by remember { mutableStateOf<Clase?>(null) }

    LaunchedEffect(userId) {
        historialGaficoViewModel.obtenerAlumnoData(userId)
        historialGaficoViewModel.obtenerClasesAprobadas( userId)
        historialGaficoViewModel.obtenerClaseData()
    }

    LaunchedEffect(alumnoData?.facultadId) {
        alumnoData?.facultadId?.let { facultadId ->
            historialGaficoViewModel.obtenerCarreraClasesData(facultadId)
        }
    }

    Scaffold(
        topBar = { MyTopAppBar(title = "Historial gráfico", navController) }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            carreraCBState?.let { carreraCB ->
                val bloquesAgrupados = carreraCB.result.groupBy { it.id_bloque }

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    bloquesAgrupados.forEach { (idBloque, clases) ->
                        item {
                            Text(
                                text = "$idBloque",
                                modifier = Modifier.padding(8.dp),
                                fontSize = 20.sp
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp)
                            ) {

                                items(clases) { clase ->
                                    // Verificar si la clase está aprobada
                                    val isAprobada = clasesAprobadas.any { it.id_clase == clase.id_clase }

                                    val cardBackgroundColor = if (isAprobada) VerdeUnicah else AmarilloUnicah // Verde claro si está aprobada, blanco si no

                                    OutlinedCard(
                                        modifier = Modifier
                                            .height(100.dp)
                                            .wrapContentWidth()
                                            .padding(2.dp)
                                            .clickable {
                                                claseSeleccionada = claseData.find { it.id_clase == clase.id_clase }
                                                showDialog = true
                                            },
                                        colors = CardDefaults.outlinedCardColors(
                                            containerColor = cardBackgroundColor
                                        )
                                    ) {
                                        Column (Modifier.fillMaxSize()){
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(cardBackgroundColor)
                                                    .weight(0.5f)

                                            ) {
                                                Column(Modifier.padding(15.dp)) {
                                                    Text(clase.id_clase,
                                                        modifier = Modifier
                                                            .padding(10.dp),
                                                        color = Color.White)
                                                }
                                            }
                                            HorizontalDivider(thickness = 1.dp)
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .weight(1f)
                                                    .background(Color.White)
                                            ) {
                                                Column(Modifier.padding(15.dp)) {
                                                    val claseNombre = claseData.find { it.id_clase == clase.id_clase }
                                                    Text(claseNombre?.nombre_clase.toString())
                                                }
                                            }
                                        }

                                    }
                                }

                            }
                        }
                    }
                }
            } ?: Box(
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
    if (showDialog && claseSeleccionada != null) {
        var isChecked by remember { mutableStateOf(false) }
        var labelText by remember { mutableStateOf("Pendiente") }
        var loading by remember { mutableStateOf(false) }

        // Validar si la clase está aprobada
        LaunchedEffect(claseSeleccionada) {
            historialGaficoViewModel.obtenerClasesAprobadas(userId)
            val clasesAprobadas = historialGaficoViewModel.clasesAprobadas.value
            val claseAprobada = clasesAprobadas.any { it.id_clase == claseSeleccionada?.id_clase }
            isChecked = claseAprobada // Marcado si no está aprobada
            labelText = if (claseAprobada) "Aprobada" else "Pendiente"
        }

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = claseSeleccionada?.nombre_clase ?: "Sin nombre")
            },
            text = {
                Column {
                    Row {
                        Text(text = claseSeleccionada?.id_clase ?: "Sin ID")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = "Créditos: ${claseSeleccionada?.creditos ?: "Sin datos"}")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = isChecked,
                            onCheckedChange = {
                                isChecked = it
                                labelText = if (it) "Aprobada" else "Pendiente"
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = VerdeUnicah,
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color.Gray,
                                uncheckedBorderColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = labelText)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (loading) {
                        Text(text = "Cargando...", color = Color.Gray)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        loading = true
                        if (isChecked) {
                            // Aprobar la clase
                            historialGaficoViewModel.aprobarClase(
                                alumnoId = userId,
                                idClase = claseSeleccionada?.id_clase ?: "",
                                notaFinal = "70"
                            )
                        } else {
                            val idClaseSeleccionada = claseSeleccionada?.id_clase

                            if (!idClaseSeleccionada.isNullOrEmpty()) {
                                historialGaficoViewModel.eliminarClaseAprobada(idClaseSeleccionada)
                            } else {
                                Log.e("EliminarClase", "El id_clase no es válido o está vacío.")
                            }

                        }
                        loading = false
                        showDialog = false
                    },
                    enabled = !loading
                ) {
                    Text("Confirmar", color = VerdeUnicah)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar", color = Color.Red)
                }
            }
        )


    }

}




//            LazyRow(
//                Modifier.fillMaxWidth()
//            ) {
//                item {
//                    Box(
//                        Modifier.height(100.dp)
//                    ) {
//                        Text(
//                            "I", fontSize = 35.sp,
//                            modifier = Modifier
//                                .padding(start = 15.dp)
//                                .align(Alignment.Center)
//                        )
//                    }
//                }
//
//                items(2) {
//                    OutlinedCard(
//                        Modifier
//                            .wrapContentWidth()
//                            .height(100.dp)
//                            .padding(15.dp)
//                            .clickable { showDialog = true },
//                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
//                        border = if (aprobadoViewModel.isChecked.value) {
//                            BorderStroke(2.dp, VerdeUnicah)
//                        } else {
//                            BorderStroke(1.dp, Color.Gray)
//                        }
//                    ) {
//                        Column {
//                            Text(
//                                text = "clase",
//                                fontSize = 12.sp,
//                                modifier = Modifier.padding(15.dp)
//                            )
//                            Text(
//                                text = if (aprobadoViewModel.isChecked.value) "APROBADO" else "PENDIENTE",
//                                fontSize = 8.sp,
//                                color = if (aprobadoViewModel.isChecked.value) VerdeUnicah else Color.Gray,
//                                modifier = Modifier.padding(start = 15.dp)
//                            )
//                        }
//                    }
//                }
//            }
//            HorizontalDivider(thickness = 1.dp)


/*        if (showDialog) {
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
    }*/
