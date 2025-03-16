package com.devproy.pensummovil.Navigation


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.height
import com.devproy.pensummovil.ui.theme.AzulUnicah

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
            )
        },
        Modifier
            .height(65.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AzulUnicah
        )
    )
}