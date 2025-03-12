package com.devproy.pensummovil.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title: String) {
    TopAppBar(
        title = { Text(text = title) }
    )
}

@Composable
fun TopAppBar(title: String, showTopBar: Boolean = true) {
    Scaffold(
        topBar = {
            if (showTopBar) {
                MyTopBar(title = title)
            }
        }
    ) { padding ->
        Column() {
            Text(text = "$title", modifier = Modifier.padding(16.dp))
        }
    }
}
