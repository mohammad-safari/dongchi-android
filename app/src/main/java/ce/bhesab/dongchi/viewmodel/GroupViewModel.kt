package ce.bhesab.dongchi.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ce.bhesab.dongchi.api.group.model.GroupBalanceRetrievalResponse
import ce.bhesab.dongchi.api.group.model.GroupEventRetrievalResponse
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Transaction
import ce.bhesab.dongchi.repository.GroupRepository
import ce.bhesab.dongchi.repository.UserSettingsRepository
import kotlinx.coroutines.launch

class GroupViewModel(private val context: Context) : ViewModel() {
    private var _error = mutableStateOf(false)
    val fetchErrorState: State<Boolean> = _error

    private val groupRepository = GroupRepository()
    private val userSettingsRepository = UserSettingsRepository(context)

    private val _balanceList = mutableStateOf<List<Balance>>(emptyList())
    val balances: State<List<Balance>> = _balanceList

    private val _eventList = mutableStateOf<List<Event>>(emptyList())
    val events: State<List<Event>> = _eventList

    fun fetchBalances(groupId: String) {
        _error.value = false

        viewModelScope.launch {
            val authorizationHeader = userSettingsRepository.getToken().getOrNull()
            val response = authorizationHeader?.let { groupRepository.fetchBalances(groupId, it) }
            if(response == null){
                _error.value = true
                _balanceList.value = listOf<Balance>(
                    Balance("Sarvenaz", mapOf("Amirhossein" to 30.0)),
                    Balance("Mohammad", mapOf("Amirhossein" to 20.0))
                )
            }
            else {
                _balanceList.value = convertBalances(response)
            }
        }
    }

    fun fetchEvents(groupId: String) {
        _error.value = false

        viewModelScope.launch {
            val authorizationHeader = userSettingsRepository.getToken().getOrNull()
            val response = authorizationHeader?.let { groupRepository.fetchEvents(groupId, it) }
            if(response == null){
                _error.value = true
                _eventList.value = listOf<Event>(
                    Transaction(22.2, "Sarvenaz", "Mohammad"),
                    Expense(30.0, "Amirhossein", "Cake")
                )
            }
            else {
                _eventList.value = convertEvents(response)
            }
        }
    }

    private fun convertBalances(balances: GroupBalanceRetrievalResponse): List<Balance>{
        val result = balances.netBalanceMap.entries.toList()
        return result.map {
            Balance(user = it.key, values = it.value.mapValues { it.value.toDouble() })
        }
    }

    private fun convertEvents(events: List<GroupEventRetrievalResponse>) : List<Event> {
        return events.map { if (it.type == "Expense") Expense(it.totalAmount.toDouble(), it.creditorUsername,"") else Transaction(it.totalAmount.toDouble(), it.creditorUsername, it.participants[0])}
    }
}