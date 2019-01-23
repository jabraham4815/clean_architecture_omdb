
package launcher.farrago.com.domain.usecase

import android.util.Log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import launcher.farrago.com.domain.exceptions.Failure
/*
   UseCase abstraction responsibility is to accept a map of parameters as input and
   execute the assigned unit of work , then return the result.

   All task are started in 'IO' thread and results are posted to 'MAIN'
 */
abstract class UseCase<in Params, out Type> where Type : Any? {

    abstract suspend fun run(params: Params?): Either<Failure, Type>
    private lateinit var job: Deferred<Either<Failure, Type>>

    operator fun invoke(
        params: Params? = null,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        Log.d("USECASE","UseCase.invoke Enter")
        /*
            Will execute whatever task asked to do
         */
        job = GlobalScope.async(Dispatchers.IO) { run(params) }

        /*
          Waits for the unit of work defined in Concrete class
         */
        GlobalScope.launch(Dispatchers.Main) {
            Log.d("USECASE","UseCase.invoke started Exec  onResult(job.await())")
            onResult(job.await())
            Log.d("USECASE","UseCase.invoke started Exec  onResult(job.await()) Done")
        }
        Log.d("USECASE","UseCase.invoke Exit")
    }

    fun cancelUseCase() {
        if (::job.isInitialized && !job.isCancelled) {
            job.cancel()
        }
    }

}