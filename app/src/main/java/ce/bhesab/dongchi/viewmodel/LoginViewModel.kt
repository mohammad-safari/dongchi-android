package ce.bhesab.dongchi.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ce.bhesab.dongchi.api.RetrofitClient
import ce.bhesab.dongchi.api.user.model.LoginRequest
import ce.bhesab.dongchi.repository.UserSettingsRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    context : Context
) : ViewModel() {

    private val userApi =  RetrofitClient.userApi
    private val userSettingsRepository =  UserSettingsRepository(context)

    private var _loginSuccess = mutableStateOf(false)
    val loginSuccess = _loginSuccess

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = userApi.login(LoginRequest( "", email, "", password))


                if (response.isSuccessful) {
                    val token = response.body()?.token ?: ""
                    userSettingsRepository.saveToken(token)
                    _loginSuccess.value = true
                } else {
                    // Handle login failure
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
            }
        }
    }
}
