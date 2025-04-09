package com.devproy.pensummovil.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devproy.pensummovil.ApiService.PensumApiService
import com.devproy.pensummovil.ApiService.RetrofitInstance
import com.devproy.pensummovil.Model.Alumno
import com.devproy.pensummovil.Model.Aprobado
import com.devproy.pensummovil.Model.CarreraBloqueResponse
import com.devproy.pensummovil.Model.CarreraCBloque
import com.devproy.pensummovil.Model.Clase
import com.devproy.pensummovil.Model.ClaseResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistorialGaficoViewModel : ViewModel() {
    //Variables para carrera clase bloque
    private val _CarreraCBData = MutableStateFlow<CarreraBloqueResponse?>(null)
    val carreraCBData: StateFlow<CarreraBloqueResponse?> = _CarreraCBData
    //Variable para Alumni
    private val _AlumnoData = MutableStateFlow<Alumno?>(null)
    val alumnoData: StateFlow<Alumno?> = _AlumnoData
    //varible para clase
    private val _ClaseData = MutableStateFlow<List<Clase>>(emptyList())
    val claseData: StateFlow<List<Clase>> = _ClaseData

    private val _ClasesAprobadas = MutableStateFlow<List<Aprobado>>(emptyList())
    val clasesAprobadas: StateFlow<List<Aprobado>> = _ClasesAprobadas

    // Obtener datos del alumno usando userId igualado a alumnoId
    fun obtenerAlumnoData(userId: String) {
        viewModelScope.launch {
            try {
                val alumnoResponse = RetrofitInstance.api.obtenerAlumno()
                val alumnoEncontrado = alumnoResponse.result.find { it.alumnoId == userId }
                _AlumnoData.value = alumnoEncontrado
            } catch (e: Exception) {
                e.printStackTrace()
                _AlumnoData.value = null // Limpia los datos en caso de error
            }
        }
    }

    fun obtenerClaseData() {
        viewModelScope.launch {
            try {
                val claseResponse = RetrofitInstance.api.obtenerClase()
                _ClaseData.value = claseResponse.result
            } catch (e: Exception) {
                e.printStackTrace()
                _ClaseData.value = emptyList() // Limpiar datos en caso de error
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

    // Obtener las clases filtradas por el facultadId del alumno
    fun obtenerCarreraClasesData(facultadId: String) {
        viewModelScope.launch {
            try {
                val carreraCBResponse = RetrofitInstance.api.obtenerCarreraCBloque()
                val clasesFiltradas = carreraCBResponse.result.filter { it.facultadId == facultadId }
                _CarreraCBData.value = CarreraBloqueResponse(result = clasesFiltradas)
                val bloquesAgrupados = agruparClasesBloque(clasesFiltradas)

                // Mapear los bloques agrupados para enviarlos a la interfaz
                _CarreraCBData.value = CarreraBloqueResponse(result = bloquesAgrupados.flatMap { it.value })
            } catch (e: Exception) {
                e.printStackTrace()
                _CarreraCBData.value = null
            }
        }
    }

    // Función para aprobar una clase
    fun aprobarClase(alumnoId: String, idClase: String, notaFinal: String) {
        viewModelScope.launch {
            try {
                val aprobado = Aprobado(
                    id_aprobado = 0, // Se asigna automáticamente en la BD
                    alumnoId = alumnoId,
                    id_clase = idClase,
                    nota_final = notaFinal
                )

                val response = RetrofitInstance.api.insertAprobado(aprobado)
                if (response.isSuccessful) {
                    Log.d("HistorialGrafico", "Clase aprobada con éxito.")
                } else {
                    Log.e("HistorialGrafico", "Error al aprobar clase: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("HistorialGrafico", "Excepción al aprobar clase: ${e.message}")
            }
        }
    }

    // Función para eliminar una clase aprobada
    fun eliminarClaseAprobada(idClase: String) {
        viewModelScope.launch {
            try {
                // Construir el cuerpo de la petición con id_clase
                val body = mapOf(
                    "id_clase" to idClase // Enviar solo id_clase
                )
                val response = RetrofitInstance.api.deleteAprobado(body)
                if (response.isSuccessful) {
                    Log.d("EliminarClase", "Clase aprobada eliminada exitosamente.")
                } else {
                    Log.e("EliminarClase", "Error al eliminar clase: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("EliminarClase", "Excepción: ${e.message}")
            }
        }
    }

    fun agruparClasesBloque(carreraCBData: List<CarreraCBloque>): Map<Int, List<CarreraCBloque>> {
        return carreraCBData.groupBy { it.id_bloque }
    }

}

