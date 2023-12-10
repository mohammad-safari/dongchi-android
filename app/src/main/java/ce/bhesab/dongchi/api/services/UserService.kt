package ce.bhesab.dongchi.api.services

import ce.bhesab.dongchi.api.RetrofitClient
import ce.bhesab.dongchi.api.interfaces.UserApi
import retrofit2.create

class UserService {
    private val retrofit = RetrofitClient.getClient()
    private val userApi = retrofit.create<>(UserApi::class.java)
    fun getProfile() {
        val profileResponse = userApi.getProfile().execute()
        val successful = profileResponse.isSuccessful
        val httpStatusCode = profileResponse.code()
        val httpStatusMessage = profileResponse.message()
    }
}