package ce.bhesab.dongchi.api

import ce.bhesab.dongchi.api.group.GroupInfoApi
import ce.bhesab.dongchi.api.user.GroupApi
import ce.bhesab.dongchi.api.user.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://10.0.2.2:8080/"

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi: UserApi = retrofit.create(UserApi::class.java)
    val groupApi: GroupApi = retrofit.create(GroupApi::class.java)
    val groupInfoApi: GroupInfoApi = retrofit.create(GroupInfoApi::class.java)
}