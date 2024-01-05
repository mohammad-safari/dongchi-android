package ce.bhesab.dongchi.api.user

import ce.bhesab.dongchi.api.user.model.LoginRequest
import ce.bhesab.dongchi.api.user.model.LoginResponse
import ce.bhesab.dongchi.api.user.model.SignupRequest
import ce.bhesab.dongchi.api.user.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


public interface UserApi {
    @POST("/authentication/register")
    @Headers("Content-Type: application/json")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>

    @POST("/authentication/login")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/user/profile")
    suspend fun profile(@Header("Authorization") authorizationHeader: String): Response<Any>
}