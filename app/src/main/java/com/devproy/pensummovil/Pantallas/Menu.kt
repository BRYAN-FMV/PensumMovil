package com.devproy.pensummovil.Pantallas

import android.R.color.black
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.devproy.pensummovil.R
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devproy.pensummovil.Navigation.TopAppBar
import com.devproy.pensummovil.ui.theme.*

@Composable
fun MenuPrincipal (navController: NavHostController){
    Column(Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(15.dp)) {
        Row(Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .clickable(onClick = {navController.navigate("historial_grafico")}),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.historialgrafico),
                contentDescription = "historial grafico icono"
            )
            Text("Historial grafico", fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))
        }
        HorizontalDivider(thickness = 1.dp)
        Row(Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .clickable(onClick = {navController.navigate("listado_clases")}),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.listado),
                contentDescription = "listado de clases icono"
            )
            Text("listado de clases", fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))
        }
        HorizontalDivider(thickness = 1.dp)
    }
}