package ce.bhesab.dongchi.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ce.bhesab.dongchi.repository.UserSettingsRepository
import kotlinx.coroutines.launch

class BaseViewModel(private val context: Context) : ViewModel() {
    private val settingsRepository = UserSettingsRepository(context) // and other repositories

    private var _error = mutableStateOf(false)
    var fetchErrorState by _error

    private val _isLogin = mutableStateOf<Boolean>(false)
    val isLogin: State<Boolean> = _isLogin


    fun checkLogin() {
        viewModelScope.launch {
        if (settingsRepository.getToken() != null)
            _isLogin.value = true
        }
    }

    fun getPreference() {}

    fun fetchProfile() {}

}
