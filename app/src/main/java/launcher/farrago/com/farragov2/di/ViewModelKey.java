package launcher.farrago.com.farragov2.di;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;
/*
ViewModelKey annotation, when used on provider methods,
basically says that the services returned by these methods must be put into special Map.
The keys in this Map will be of type Class<? extends ViewModel>
and the values will be of type <? extends ViewModel> (subclass of ViewModel).
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@interface ViewModelKey {
    Class<? extends ViewModel> value();
}
