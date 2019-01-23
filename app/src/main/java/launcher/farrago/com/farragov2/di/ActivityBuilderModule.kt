package launcher.farrago.com.farragov2.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import launcher.farrago.com.data.di.DataModuleScoped
import launcher.farrago.com.farragov2.MainActivity
import launcher.farrago.com.farragov2.ContentDetailsActivity
import javax.inject.Singleton

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [DataModuleScoped::class])
    @Singleton
    abstract fun bindMainActivity (): MainActivity

    @ContributesAndroidInjector(modules = [DataModuleScoped::class])
    //@ContributesAndroidInjector
    @Singleton
    abstract fun bindOtherActivity (): ContentDetailsActivity
}