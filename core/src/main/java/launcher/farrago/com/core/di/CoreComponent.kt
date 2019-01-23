package launcher.farrago.com.core.di

import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
/*
   We cannot use a subcomponent in core because we would have a circular dependency with other Modules
   and dagger-android has no use in Core since we are not really injecting anything there,
   just providing dependencies.
 */
@Component(modules = [CoreModule::class])
@Singleton
interface CoreComponent {
    fun getOkHttpClient(): OkHttpClient?
    fun getGson(): Gson
    fun getRetrofit(): Retrofit
}