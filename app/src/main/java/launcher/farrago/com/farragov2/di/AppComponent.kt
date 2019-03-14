package launcher.farrago.com.farragov2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import launcher.farrago.com.core.di.CoreComponent
import launcher.farrago.com.farragov2.ContentsView
import launcher.farrago.com.farragov2.MainApplication

/*
 @Component: Component is a graph. We build a component. Component will built it's graph and provide injected instances by using modules.
 */

/*
    //When using ComponentDependency
    @Component(modules=arrayOf(AppModule::class, AndroidInjectionModule::class, ActivityBuilderModule::class,ExpensiveModule::class),dependencies = arrayOf(CoreComponent::class,DataComponent::class))
*/
@Component(
    modules = arrayOf(AppModule::class, AndroidInjectionModule::class, ActivityBuilderModule::class),
    dependencies = arrayOf(CoreComponent::class)
)
@AppScope
interface AppComponent {
    /*
    @Component.Builder: We might want to bind some INSTANCE to Component.
    In this case we can create an interface with @Component.Builder annotation
    and add whatever method we want to add to builder.
    In this case we wanted to add Application to my AppComponent.
    */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun coreComponent(coreComponent: CoreComponent): AppComponent.Builder
        /*
            //When using ComponentDependency
            fun dataComponent(dataComponent: DataComponent): AppComponent.Builder
         */

        fun build(): AppComponent
    }

    /*
      Following are the classes requesting injection.
      i.e asking access to the graph of objects built by this component.
     */
    fun inject(app: MainApplication)
    fun inject(contentView: ContentsView)
}