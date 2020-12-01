package org.company.annamedvedieva.imagebrowser.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(clientId: String) : Interceptor {

    val id: String = clientId
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "Client-ID $id").build()
        return chain.proceed(request)
    }
}