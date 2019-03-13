package launcher.farrago.com.core.omdb.rest

import android.support.annotation.NonNull
import launcher.farrago.com.domain.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", Constants.API_OMDB_KEY)
            .build()

        val url2 = url.toString().replace("%3D", "=")

        val request = originalRequest.newBuilder().url(url2).build()
        return chain.proceed(request)
    }
}
