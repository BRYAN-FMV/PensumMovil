package com.devproy.pensummovil.Pantallas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*

import com.devproy.pensummovil.Navigation.MyTopAppBar
import com.devproy.pensummovil.ViewModel.AprobadoViewModel
import com.devproy.pensummovil.ui.theme.VerdeUnicah

@Composable
fun ListadoClases(viewModel: AprobadoViewModel) {

    Scaffold(
        topBar = { MyTopAppBar(title = "Listado de clases") }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.isChecked.value) {
                Text("aprobada", fontSize = 20.sp, color = VerdeUnicah)
            } else {
                Text("no hay clases aprobadas", fontSize = 20.sp, color = Color.Red)
            }

        }

    }
}