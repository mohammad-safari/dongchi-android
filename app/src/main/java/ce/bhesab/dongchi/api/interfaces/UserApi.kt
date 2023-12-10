package ce.bhesab.dongchi.api.interfaces

import ce.bhesab.dongchi.api.models.user.AuthResponse
import ce.bhesab.dongchi.api.models.user.ProfileResponse
import ce.bhesab.dongchi.api.models.user.RegisterResponse
import ce.bhesab.dongchi.api.models.user.RegisterationRequest
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("profile")
    fun getProfile(): ProfileResponse

//    @POST("registration")
//    fun register(body: RegisterationRequest): RegisterResponse
//
//    @POST("authentication")
//    fun auth(): AuthResponse

}