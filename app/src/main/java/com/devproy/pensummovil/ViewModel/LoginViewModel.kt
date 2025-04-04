import androidx.lifecycle.ViewModel
import com.devproy.pensummovil.ApiService.RetrofitInstance
import com.devproy.pensummovil.Model.LoginResponse
import com.devproy.pensummovil.Model.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class LoginViewModel : ViewModel() {

    private val _loginResult = MutableStateFlow<LoginResponse?>(null)
    val loginResult = _loginResult.asStateFlow() // 🔹 Exponer como StateFlow

    fun login(userId: String, password: String) {
        val user = UserResponse(userId, password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.login(user)
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                } else {
                    _loginResult.value = null
                }
            } catch (e: Exception) {
                _loginResult.value = null
            }
        }
    }
}
