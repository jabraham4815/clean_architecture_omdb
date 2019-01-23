package launcher.farrago.com.core.rest

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    companion object {

        /*Instance*/
        var instance: HeaderInterceptor = HeaderInterceptor()
            private set
        var CONTENT_TYPE: String = "Content-Type"
        var APPLICATION_JSON: String = "application/json"
    }

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val request: Request? = chain?.request()
        val requestBuilder: Request.Builder? = request?.newBuilder()
        requestBuilder?.let {
            it.addHeader(CONTENT_TYPE, APPLICATION_JSON)
            return chain.proceed(it.build())
        }
        return null
    }
}