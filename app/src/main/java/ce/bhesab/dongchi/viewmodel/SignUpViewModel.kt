package ce.bhesab.dongchi.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import ce.bhesab.dongchi.api.RetrofitClient
import ce.bhesab.dongchi.api.user.model.SignupRequest
import ce.bhesab.dongchi.repository.UserSettingsRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel


class SignUpViewModel(context : Context) : ViewModel() {

    private val userApi =  RetrofitClient.userApi
    private val userSettingsRepository =  UserSettingsRepository(context)

    private var _signUoSuccess = mutableStateOf(false)
    val signUpSuccess = _signUoSuccess

    fun signUpUser(name: String, email: String,phone : String , password: String ) {
        viewModelScope.launch {
            try {
                val response = userApi.signup(SignupRequest(name, email, phone, password))
                if (response.isSuccessful) {
                    val token = response.body()?.token ?: ""
                    userSettingsRepository.saveToken(token)
                    _signUoSuccess.value = true
                } else {
                    // Handle signup failure
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
            }
        }
    }
}