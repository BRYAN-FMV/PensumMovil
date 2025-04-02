package com.devproy.pensummovil.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devproy.pensummovil.ApiService.RetrofitInstance
import com.devproy.pensummovil.Model.ClaseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClaseViewModel : ViewModel() {
    private val _claseData = MutableStateFlow<ClaseResponse?>(null)
    val claseData: StateFlow<ClaseResponse?> = _claseData

    fun obtenerClaseData() {
        viewModelScope.launch {
            try {
                val claseResponse = RetrofitInstance.api.obtenerClase()
                _claseData.value = claseResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
