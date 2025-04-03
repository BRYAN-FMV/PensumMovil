package com.devproy.pensummovil.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devproy.pensummovil.ApiService.RetrofitInstance
import com.devproy.pensummovil.Model.Alumno
import com.devproy.pensummovil.Model.AlumnoResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlumnoViewModel : ViewModel() {
    private val _alumnoData = MutableStateFlow<Alumno?>(null) // Solo guardamos un alumno
    val alumnoData: StateFlow<Alumno?> = _alumnoData

    private val _alumnoId = MutableStateFlow("")
    val alumnoId: StateFlow<String> = _alumnoId

    fun obtenerAlumnoData(alumnoId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.obtenerAlumno()
                val alumnoEncontrado = response.result.find { it.alumnoId == alumnoId } // Filtramos por ID

                if (alumnoEncontrado != null) {
                    _alumnoData.value = alumnoEncontrado
                    _alumnoId.value = alumnoId
                } else {
                    _alumnoData.value = null // Si no se encuentra, limpiamos los datos
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
