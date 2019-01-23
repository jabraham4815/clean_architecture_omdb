package launcher.farrago.com.farragov2

import android.app.Activity
import android.app.Application
import android.util.Log
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import launcher.farrago.com.core.di.CoreComponent
import launcher.farrago.com.core.di.CoreComponentProvider
import launcher.farrago.com.core.di.DaggerCoreComponent
import launcher.farrago.com.farragov2.di.AppComponent
import launcher.farrago.com.farragov2.di.DaggerAppComponent
import javax.inject.Inject

class MainApplication : Application(), CoreComponentProvider, HasActivityInjector {
    private lateinit var coreComponent: CoreComponent
    private lateinit var appComponent: AppComponent

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    /*
       or

        var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
        @Inject set
     */

    override fun onCreate() {
        super.onCreate()
        provideAppComponent()
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    /*
        Application class build a graph using AppComponent.
        When AppComponent is build with its modules, we have a graph with all provided instances in our graph.
        For INSTANCE, If app module provides ApiService, we will have ApiService INSTANCE when we build component which has app module.
        Here we are binding our application INSTANCE to our Dagger graph.
     */
    fun provideAppComponent(): AppComponent {
        Log.d("DaggerOMDB", "Enter provideAppComponent")
        if (!this::appComponent.isInitialized) {

        }

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .coreComponent(provideCoreComponent())
            .build()

        appComponent.inject(this)
        return appComponent
    }

    override fun provideCoreComponent(): CoreComponent {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .build()
        }
        return coreComponent
    }
}