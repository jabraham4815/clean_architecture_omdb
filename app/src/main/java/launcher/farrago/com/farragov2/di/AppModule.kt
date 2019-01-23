package launcher.farrago.com.farragov2.di

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import launcher.farrago.com.farragov2.MainApplication
import javax.inject.Singleton

/*
  We provide context, retrofit, okhttp, persistence db, shared pref etc here.
 */
@Module
class AppModule() {
    @Provides
    @AppScope
    fun provideContext(app: Application): Context {
        Log.d("DaggerOMDB", "Enter provideContext")
        return app
    }

    @Provides
    @AppScope
    fun provideApplication(app: MainApplication): MainApplication {
        Log.d("DaggerOMDB", "Enter provideApplication")
        return app
    }
}
