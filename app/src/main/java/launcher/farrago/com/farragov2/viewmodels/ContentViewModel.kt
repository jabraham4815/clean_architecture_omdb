package launcher.farrago.com.farragov2.viewmodels

import android.arch.lifecycle.MutableLiveData
import launcher.farrago.com.data.usecases.GetContentsUseCase
import launcher.farrago.com.domain.exceptions.Failure
import launcher.farrago.com.domain.models.Content
import launcher.farrago.com.domain.usecase.Either
import javax.inject.Inject

/*
  Ideally all dependencies for view model should have been injected via Ctr
 */
class ContentViewModel @Inject constructor ( var getContentsUseCase: GetContentsUseCase<Map<String, String>>): BaseViewModel() {
    private var _contents: MutableLiveData<List<Content?>>? = null
    val contents: MutableLiveData<List<Content?>>
        get() {
            if (_contents == null) {
                _contents = MutableLiveData()
            }
            return _contents ?: throw AssertionError("Set to null by another thread")
        }

    fun getContents(inputParams: Map<String, String>) {
        getContentsUseCase(inputParams, onResult = { it: Either<Failure, List<Content?>> ->
            it.either(::handleFailure, ::handleContentsFound)
        })
    }

    private fun handleContentsFound(contents: List<Content?>) {
        this.contents.value = contents
    }

    override fun onCleared() {
        super.onCleared()
        getContentsUseCase.cancelUseCase()
    }
}