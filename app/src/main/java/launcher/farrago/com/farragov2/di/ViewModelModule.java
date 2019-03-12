package launcher.farrago.com.farragov2.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import launcher.farrago.com.data.usecases.GetContentsUseCase;
import launcher.farrago.com.farragov2.viewmodels.ContentViewModel2;

@Module
public class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(ContentViewModel2.class)
        /*
      Note that the return type of the provider method is ViewModel and not ViewModel1.
      It’s intentional. @IntoMap annotation says that Provider object for this service will be inserted into Map,
      and @ViewModelKey annotation specifies under which key it will reside.
     */
    @Inject
    ViewModel contentCViewModel2(
           GetContentsUseCase<Map<String, String>> usecase) {
        return new ContentViewModel2(usecase);
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

    //public class ViewModelFactory implements ViewModelProvider.Factory {
    //
    //    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mProviderMap;
    //
    //    @Inject
    //    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    //        mProviderMap = providerMap;
    //    }
    //
    //    @SuppressWarnings("unchecked")
    //    @NonNull
    //    @Override
    //    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    //        return (T) mProviderMap.get(modelClass).get();
    //    }
    //}

}
