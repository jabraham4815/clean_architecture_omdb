package launcher.farrago.com.data.api.omdb.rest

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

        /*
          [JA] Blocked on encoding issues and not able to remove = encoding.
          For now this works or consider brute force by using Asynkk and doing direct Json deserialization
         */
        val url2 = url.toString().replace("%3D", "=")

        val request = originalRequest.newBuilder().url(url2).build()
        return chain.proceed(request)
    }
}