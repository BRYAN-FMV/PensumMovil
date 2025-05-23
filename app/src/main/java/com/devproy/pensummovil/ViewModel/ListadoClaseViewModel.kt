package com.devproy.pensummovil.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devproy.pensummovil.ApiService.RetrofitInstance
import com.devproy.pensummovil.Model.Alumno
import com.devproy.pensummovil.Model.Aprobado
import com.devproy.pensummovil.Model.AprobadoResponse
import com.devproy.pensummovil.Model.Clase
import com.devproy.pensummovil.Model.ClaseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListadoClaseViewModel : ViewModel() {

    // Variables para almacenar el estado de alumno y clases aprobadas
    private val _alumnoData = MutableStateFlow<Alumno?>(null)
    val alumnoData: StateFlow<Alumno?> = _alumnoData

    private val _aprobadoData = MutableStateFlow<AprobadoResponse?>(null)
    val aprobadoData: StateFlow<AprobadoResponse?> = _aprobadoData


    private val _ClaseData = MutableStateFlow<List<Clase>>(emptyList())
    val claseData: StateFlow<List<Clase>> = _ClaseData

    private val _ClasesAprobadas = MutableStateFlow<List<Aprobado>>(emptyList())
    val clasesAprobadas: StateFlow<List<Aprobado>> = _ClasesAprobadas

    // Función para obtener datos del alumno y las clases aprobadas
    fun obtenerAlumnoData(alumnoId: String) {
        viewModelScope.launch {
            try {
                // Obtener los datos de los alumnos desde la API
                val alumnoResponse = RetrofitInstance.api.obtenerAlumno()
                val alumnoEncontrado = alumnoResponse.result.find { it.alumnoId == alumnoId }

                if (alumnoEncontrado != null) {
                    _alumnoData.value = alumnoEncontrado

                    // Obtener las clases aprobadas del alumno
                    val aprobadoResponse = RetrofitInstance.api.obtenerAprobado(alumnoId)
                    _aprobadoData.value = aprobadoResponse.body()

                    //Obtener datos de las clases
                    val claseResponse = RetrofitInstance.api.obtenerClase()
                    _ClaseData.value = claseResponse.result

                } else {
                    _alumnoData.value = null // Si no se encuentra el alumno, limpiar los datos
                    _aprobadoData.value = null // Limpiar las clases aprobadas
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun obtenerClasesAprobadas(alumnoId: String) {
        viewModelScope.launch {
            try {
                // Llamar al endpoint de Retrofit
                val response = RetrofitInstance.api.obtenerAprobado(alumnoId)

                if (response.isSuccessful && response.body() != null) {
                    _ClasesAprobadas.value = response.body()!!.result
                    Log.d("HistorialGrafico", "Clases aprobadas obtenidas con éxito.")
                }
            } catch (e: Exception) {
                // Capturar cualquier excepción
                Log.e("HistorialGrafico", "Excepción al obtener clases aprobadas: ${e.message}")
            }
        }
    }
}

