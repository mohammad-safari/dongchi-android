package ce.bhesab.dongchi.api.user

import ce.bhesab.dongchi.api.user.model.EventPostRequest
import ce.bhesab.dongchi.api.user.model.GroupResponse
import ce.bhesab.dongchi.api.user.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface GroupApi {
    @GET("/group")
    suspend fun getGroup(@Header("Authorization") token : String) : Response<GroupResponse>
    @POST("/group/event")
    suspend fun postGroupEvent(
        @Header("Authorization") authorizationHeader: String,
        @Body eventPostRequest: EventPostRequest
    ): Response<SignupResponse>
}