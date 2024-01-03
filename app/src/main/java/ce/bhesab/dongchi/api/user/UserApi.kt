package ce.bhesab.dongchi.api.user

import ce.bhesab.dongchi.api.user.model.LoginRequest
import ce.bhesab.dongchi.api.user.model.LoginResponse
import ce.bhesab.dongchi.api.user.model.SignupRequest
import ce.bhesab.dongchi.api.user.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


public interface UserApi {
    @POST("/authentication/register")
    suspend fun signup(@Body signupRequest: SignupRequest): Response<SignupResponse>

    @POST("/authentication/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/user/profile")
    suspend fun login(@Header("Authorization") authorizationHeader: String): Response<Any>
}