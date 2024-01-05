package ce.bhesab.dongchi.api.group

import android.content.ContentResolver.MimeTypeInfo
import ce.bhesab.dongchi.api.group.model.EventPostRequest
import ce.bhesab.dongchi.api.user.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

public interface GroupApi {
    @POST("/group/event")
    suspend fun postGroupEvent(
        @Header("Authorization") authorizationHeader: String,
        @Body eventPostRequest: EventPostRequest
    ): Response<SignupResponse>
}