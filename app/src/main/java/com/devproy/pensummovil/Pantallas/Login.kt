package com.devproy.pensummovil.Pantallas

import LoginViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devproy.pensummovil.ui.theme.AzulUnicah
import com.devproy.pensummovil.R
import com.devproy.pensummovil.ui.theme.AmarilloUnicah

// Pantalla de inicio de sesión de usuario
@Composable
fun Login(
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavHostController,
) {
    val context = LocalContext.current
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginResult by loginViewModel.loginResult.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") } // Estado para el mensaje de error

    LaunchedEffect(Unit) {
        loginViewModel.clearSession()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://login.sec.unicah.net/imgs/NewLogo.png",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 3.dp, bottom = 0.dp)
        )

        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("Usuario") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AmarilloUnicah
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Contraseña") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AmarilloUnicah
            ),
            trailingIcon = {
                val image = if (passwordVisible) R.drawable.visibilityoff else R.drawable.visibility
                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = description
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        // BOTÓN CON LÓGICA DE LOGIN
        Button(
            onClick = {
                loginViewModel.login(userId, password)
            },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulUnicah)
        ) {
            Text("INGRESAR", fontSize = 15.sp)
        }

        // MENSAJE DE ERROR
        if (errorMessage.isEmpty() || errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // NAVEGACIÓN SI LOGIN ES EXITOSO
        LaunchedEffect(loginResult) {
            loginResult?.let { response ->
                if (response.token != null) { // Si el inicio de sesión es exitoso
                    navController.navigate("menu/${response.userId}/${response.RoleId}") // Pasar RoleId
                    errorMessage = "" // Limpiar mensaje de error
                } else {
                    errorMessage = "Usuario o contraseña incorrectos. Intente de nuevo." // Mostrar mensaje de error
                }
            }
        }
    }
}




