package ce.bhesab.dongchi.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ce.bhesab.dongchi.api.RetrofitClient
import ce.bhesab.dongchi.api.user.model.GroupResponse
import ce.bhesab.dongchi.repository.UserSettingsRepository
import co.yml.charts.common.extensions.isNotNull
import kotlinx.coroutines.launch


class ShowGroupViewModel (context : Context
) : ViewModel() {

    private val groupApi =  RetrofitClient.groupApi
    private val userSettingsRepository =  UserSettingsRepository(context)


    private val _groupData = mutableStateOf<GroupResponse?>(null)
    val groupData: State<GroupResponse?> = _groupData

    suspend fun getGroups(){
        viewModelScope.launch {
            try {
                val token = userSettingsRepository.getToken()
                if (!token.isNotNull()) {
                    val response = groupApi.getGroup("Bearer $token")
                    if (response.isSuccessful) {
                        _groupData.value = response.body()
                    } else {
                        // Handle error
                    }
                } else {
                    // Handle missing token
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
            }
        }
    }

}