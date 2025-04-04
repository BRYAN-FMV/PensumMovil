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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

// Pantalla de inicio de sesión de usuario
@Composable
fun Login(
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginResult by loginViewModel.loginResult.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }

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
            label = { Text("User ID") },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Password") },
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


        //BOTÓN CON LÓGICA DE LOGIN Y NAVEGACIÓN
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

        // NAVEGACIÓN SI LOGIN ES EXITOSO
        LaunchedEffect(loginResult) {
            loginResult?.let {
                if (it.token != null) {
                    Toast.makeText(context, "Bienvenido ${it.userId}", Toast.LENGTH_LONG).show()
                    navController.navigate("menu")
                }else{
                    loginError = true
                }
            }
        }
        if (loginError) {
            Text(
                text = "Usuario o contraseña incorrectos",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}



