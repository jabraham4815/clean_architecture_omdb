package launcher.farrago.com.data.api.omdb.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import launcher.farrago.com.domain.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpManager {
    companion object {
        var instance: HttpManager = HttpManager()
            private set
    }

    /*Properties*/
    var retrofit: Retrofit? = null
    var httpClient: OkHttpClient? = null
    private var gson: Gson? = null

    init {

        /*Http Client*/
        httpClient = OkHttpClient
                .Builder()
                .addInterceptor(HeaderInterceptor.instance)
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(RequestInterceptor())
                .build()

        val gsonBuilder: GsonBuilder = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ssZ")

        gson = gsonBuilder.create()

        httpClient?.let {

            retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(Constants.API_OMDB_ENDPOINT)
                    .client(it)
                    .build()
        }
    }


}