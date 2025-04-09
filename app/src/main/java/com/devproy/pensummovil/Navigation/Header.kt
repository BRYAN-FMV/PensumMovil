package com.devproy.pensummovil.Navigation


import LoginViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.devproy.pensummovil.ui.theme.AzulUnicah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel ()

) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { // Botón para regresar
                Icon(
                    Icons.Rounded.ArrowBack, contentDescription = "Regresar", tint = Color.White
                )
            }
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) { // Ordenar los botones horizontalmente
                IconButton(onClick = {
                    loginViewModel.clearSession() // Limpia los datos de sesión
                    navController.navigate("login") // Navega a la pantalla de inicio de sesión
                }) {
                    Icon(Icons.Rounded.ExitToApp, contentDescription = "Cerrar sesión", tint = Color.White)
                }
            }
        },
        modifier = Modifier
            .height(65.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AzulUnicah
        )
    )
}

