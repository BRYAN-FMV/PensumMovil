package com.devproy.pensummovil.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.devproy.pensummovil.ui.theme.AzulUnicah


// Pantalla de inicio de sesión de usuario
@Composable
fun Login(navController: NavHostController) {
    var cuenta by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(color=Color.White)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Logo de la universidad
        AsyncImage(
            model = "https://login.sec.unicah.net/imgs/NewLogo.png",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 3.dp, bottom = 0.dp)
        )
        Text(
            "Iniciar sesión",
            fontSize = 20.sp,
            color = AzulUnicah,
            modifier = Modifier.padding(10.dp)
        )

        //TextBox para el numero de cuenta de el usuario
        OutlinedTextField(
            value = cuenta,
            onValueChange = { cuenta = it },
            label = { Text("N° Cuenta") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        //TextBox para la contraseña de el usuario
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp, top = 20.dp)
        )


        //Boton para ingresar hacia el historial grafico
        Button(
            onClick = { navController.navigate("menu") },
            modifier = Modifier
                .height(50.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AzulUnicah)
        ) {
            Text("INGRESAR",
                fontSize = 15.sp)
        }
    }
}
