package ce.bhesab.dongchi.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ce.bhesab.dongchi.api.group.model.GroupBalanceRetrievalResponse
import ce.bhesab.dongchi.dongchiApi.endpoint.group.dto.GroupEventRetrievalResponse
import ce.bhesab.dongchi.model.group.Balance
import ce.bhesab.dongchi.model.group.Event
import ce.bhesab.dongchi.model.group.Expense
import ce.bhesab.dongchi.model.group.Transaction
import ce.bhesab.dongchi.repository.GroupRepository
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {
    private var _error = mutableStateOf(false)
    val fetchErrorState: State<Boolean> = _error

    private val groupRepository = GroupRepository()

    private val _balanceList = mutableStateOf<List<Balance>>(emptyList())
    val balances: State<List<Balance>> = _balanceList

    private val _eventList = mutableStateOf<List<Event>>(emptyList())
    val events: State<List<Event>> = _eventList

    fun fetchBalances(groupId: String, authorizationHeader: String) {
        _error.value = false

        viewModelScope.launch {
            val response = groupRepository.fetchBalances(groupId, authorizationHeader)
            if(response == null){
                _error.value = true
//                _balanceList.value = listOf<Balance>(
//                    Balance("Sarvenaz", mapOf("Amirhossein" to 30)),
//                    Balance("Mohammad", mapOf("Amirhossein" to 20))
//                )
            }
            else {
                _balanceList.value = convertBalances(response)
            }
        }
    }

    fun fetchEvents(groupId: String, authorizationHeader: String) {
        _error.value = false

        viewModelScope.launch {
            val response = groupRepository.fetchEvents(groupId, authorizationHeader)
            if(response == null){
                _error.value = true
//                _eventList.value = listOf<Event>(
//                    Transaction(3, 22.2, "Sarvenaz", "Mohammad"),
//                    Expense(4, 30.0, "Amirhossein", "Cake")
//                )
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