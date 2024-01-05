package ce.bhesab.dongchi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ce.bhesab.dongchi.api.user.model.EventPostRequest
import ce.bhesab.dongchi.repository.GroupRepository
import kotlinx.coroutines.launch

class SharesSettingViewModel : ViewModel() {
    private val groupRepository = GroupRepository()

    private var _error = mutableStateOf(false)
    var fetchErrorState by _error

    fun postEvent(authToken: String, eventPostRequest: EventPostRequest) {
        viewModelScope.launch {
            groupRepository.postGroupEvent(authToken, eventPostRequest)
        }
    }

}