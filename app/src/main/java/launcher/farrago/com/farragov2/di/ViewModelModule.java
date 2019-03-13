package launcher.farrago.com.farragov2.di;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import launcher.farrago.com.data.usecases.GetContentsUseCase;
import launcher.farrago.com.farragov2.viewmodels.ContentViewModel;

@Module
public class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(ContentViewModel.class)
        /*
      Note that the return type of the provider method is ViewModel and not ViewModel1.
      It’s intentional. @IntoMap annotation says that Provider object for this service will be inserted into Map,
      and @ViewModelKey annotation specifies under which key it will reside.
     */
    @Inject
    ViewModel provideContentViewModel(
           GetContentsUseCase<Map<String, String>> usecase) {
        Log.d("OMDB", "Providing ContentViewModel");
        return new ContentViewModel(usecase);
    }

    /*
       The net result of having the above code is that Dagger will create implicit Map data structure
       filled with Provider<ViewModel> objects and put it onto the objects graph.
       You can make use of that Map by passing it into ViewModelFactory in the following

     */

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

}
