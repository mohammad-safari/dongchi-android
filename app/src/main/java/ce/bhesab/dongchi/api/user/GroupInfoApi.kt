package ce.bhesab.dongchi.api.group

import ce.bhesab.dongchi.api.group.model.GroupBalanceRetrievalResponse
import ce.bhesab.dongchi.api.group.model.GroupEventRetrievalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GroupInfoApi {
    @GET("/group/{groupId}/balances")
    suspend fun getBalances(@Path("groupId") groupId: String,
                            @Header("Authorization") authorizationHeader: String
    ): Response<GroupBalanceRetrievalResponse>

    @GET("/group/{groupId}/events")
    suspend fun getEvents(@Path("groupId") groupId: String,
                          @Header("Authorization") authorizationHeader: String
    ): Response<List<GroupEventRetrievalResponse>>
}