package ce.bhesab.dongchi.repository

import ce.bhesab.dongchi.api.RetrofitClient
import ce.bhesab.dongchi.api.group.model.EventPostRequest

class EventRepository {
    private val eventApi = RetrofitClient.groupApi
    private val groupApi = RetrofitClient
    suspend fun postGroupEvent(authToken: String, postEventRequest: EventPostRequest) {
        eventApi.postGroupEvent(authToken, postEventRequest)
    }
}