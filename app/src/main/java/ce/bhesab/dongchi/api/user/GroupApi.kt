package ce.bhesab.dongchi.api.user

import ce.bhesab.dongchi.api.user.model.GroupResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GroupApi {
    @GET("/group")
    suspend fun getGroup(@Header("Authorization") token : String) : Response<GroupResponse>
}