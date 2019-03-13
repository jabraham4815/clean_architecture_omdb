package launcher.farrago.com.farragov2.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import launcher.farrago.com.data.di.DataModule
import launcher.farrago.com.farragov2.MainActivity
import launcher.farrago.com.farragov2.ContentDetailsActivity
import javax.inject.Singleton

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [DataModule::class,ViewModelModule::class])
    @Singleton
    abstract fun bindMainActivity (): MainActivity

    @ContributesAndroidInjector(modules = [DataModule::class])
    @Singleton
    abstract fun bindOtherActivity (): ContentDetailsActivity
}