package ce.bhesab.dongchi.repository

import ce.bhesab.dongchi.api.RetrofitClient
import ce.bhesab.dongchi.api.group.model.GroupBalanceRetrievalResponse
import ce.bhesab.dongchi.dongchiApi.endpoint.group.dto.GroupEventRetrievalResponse
import retrofit2.Response

class GroupRepository {

    suspend fun fetchBalances(groupId: String, authorizationHeader: String): GroupBalanceRetrievalResponse?{
        getBalancesList(groupId, authorizationHeader).let {
            if(it is Result.Success)
                return it.data
        }
        return null
    }

    suspend fun fetchEvents(groupId: String, authorizationHeader: String): List<GroupEventRetrievalResponse>? {
        getEventsList(groupId, authorizationHeader).let {
            if(it is Result.Success)
                return it.data
        }
        return null
    }

    private suspend fun getBalancesList(groupId: String, authorizationHeader: String): Result<GroupBalanceRetrievalResponse>{
        return try {
            val response = RetrofitClient.groupApi.getBalances(groupId, authorizationHeader)
            handleApiResponse(response)
        } catch (e: Exception){
            Result.Error(e)
        }
    }

    private suspend fun getEventsList(groupId: String, authorizationHeader: String): Result<List<GroupEventRetrievalResponse>>{
        return try {
            val response = RetrofitClient.groupApi.getEvents(groupId, authorizationHeader)
            handleApiResponse(response)
        } catch (e: Exception){
            Result.Error(e)
        }
    }

    private fun <T : Any> handleApiResponse(response: Response<T>): Result<T> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.Success(body)
            } else {
                Result.Error(Exception("Empty response body"))
            }
        } else {
            Result.Error(Exception("Network request failed"))
        }
    }

}