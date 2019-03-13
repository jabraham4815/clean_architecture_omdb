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
        Log.d("OMDB", "Providing provideContext")
        return app
    }

    @Provides
    @AppScope
    fun provideApplication(app: MainApplication): MainApplication {
        Log.d("OMDB", "Providing provideApplication")
        return app
    }
}
