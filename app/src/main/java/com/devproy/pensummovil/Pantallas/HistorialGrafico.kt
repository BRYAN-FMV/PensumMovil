package com.devproy.pensummovil.Pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.random.Random

@Composable
fun HistorialGrafico() {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            LazyRow(
                Modifier
                    .fillMaxWidth()
            ) {
                items(5) {
                    Card(
                        Modifier
                            .wrapContentWidth()
                            .height(100.dp).padding(15.dp)
                    ) {
                        Text("clase ", fontSize = 17.sp, modifier = Modifier.padding(15.dp))
                    }
                }

            }
            HorizontalDivider(thickness = 1.dp)
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(navController: NavHostController) {
    TopAppBar({
        IconButton(onClick = { navController.navigate("Login") }, Modifier.fillMaxWidth()) {
                Text("Historial Grafico")
        }
    })
}
