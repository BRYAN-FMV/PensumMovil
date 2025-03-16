package com.devproy.pensummovil.ViewModel


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

//este viewModel es temporal
//es para mostrar el estado en otra pantalla

class AprobadoViewModel : ViewModel(){
    private val _isChecked = mutableStateOf(false)
    val isChecked: State<Boolean> = _isChecked

    fun setChecked(value: Boolean){
        _isChecked.value = value
    }
}