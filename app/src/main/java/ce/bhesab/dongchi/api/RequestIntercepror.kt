package ce.bhesab.dongchi.api

import okhttp3.Interceptor
import okhttp3.Response

object RequestIntercepror : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("Outgoing request to ${request.url()}")
        return chain.proceed(request)
    }
}