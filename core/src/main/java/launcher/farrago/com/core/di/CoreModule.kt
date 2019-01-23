package launcher.farrago.com.core.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import launcher.farrago.com.core.Rest.HttpManager
import launcher.farrago.com.domain.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class CoreModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return HttpManager.instance.httpClient
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return HttpManager.instance.gson
    }

    @Provides
    @Singleton
    @Inject
    fun provideRetrofit(okHttpClient: OkHttpClient,gson:Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.API_OMDB_ENDPOINT)
            .client(okHttpClient)
            .build()
    }
}
