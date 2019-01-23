package launcher.farrago.com.farragov2.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import launcher.farrago.com.domain.exceptions.Failure

/**
 * Base ViewModel class with default Failure handling.
 * @see ViewModel { @link #ViewModel }
 * @see Failure
 *
 */
abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}