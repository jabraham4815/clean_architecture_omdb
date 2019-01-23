/**
 * Copyright (C) 2018 Jose Abraham Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package launcher.farrago.com.domain.asynkk

import android.os.Handler
import android.os.Looper
import dp.com.asynckk.GenericResponse
import dp.com.asynckk.JobAlreadyCreatedException
import dp.com.asynckk.TaskResponse
import dp.com.asynckk.TaskResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

/*

  Guidelines to implement AsyncTaskk which exposes
   - execute
   - executeDeferred
   - cancel

   API's.

    If you are using execute() please override the following
    The order of execution will be as shown below.

  1. Extend TaskResponse to represent the result of the work/job you are doing.This is implemented as TaskResult<T> where T is a type of TaskResponse
        - data class GenericResponse(val value: Any?) : TaskResponse()
        - data class HtmlPageResponse(val value: String?) : TaskResponse()

  2. override onPreExecute if there is a need for a different Dispatcher/Scope

  3. override doInBackground and implement your unit of work . Choose your Dispatcher IO, Default , Main etc

  4. override onPostExecute if there is a need to process TaskResponse or update UI.

 If you are using executeDeferred() please override the following in addition to the ones listed above

  5. override deferredExecute and process the returned Deferred Type.

  6. execute cancel will stop the active task running and set the current job to null.

Note -
        There are two ways to update the UI

        Option 1 use runnable  and use RunnableDispatcher Object
        --------------------------------------------------------
        val runnable = object : Runnable {
            override fun run() {
                // code to update UI
            }
        }
        RunnableDispatcher.dispatch(coroutineScope.coroutineContext,runnable)

        Option 2 use 'updateUI' helper method
        --------------------------------------------------------
        onPostExecute(response: TaskResponse?) {
          updateUI(response) {
              // code to update UI
          }
        }

        RunnableSingleThreadDispatcher is similar to RunnableDispatcher , but this one actually creates a thread to execute the runnable

Note -
        Re-using the same instance of AsyncTask before executing 'cancel' will raise JobAlreadyCreatedException.
        Below listed is a valid sequence

        execute()
        cancel()
        execute()


 */
abstract class AsyncTaskk {
    protected var job: Job = Job()
    protected var coroutineScope = CoroutineScope(Dispatchers.Default + job)

    /*
        coordinate preExecute,postExecute and doInBackground operations
        This function works with Deferred type and is meant to be consumed from Kotlin Source
    */
    @ExperimentalCoroutinesApi fun executeDeferred() {
        asyncSequence {
            onPreExecute()
            val taskResult: Deferred<TaskResult<TaskResponse?>?>?
            taskResult = deferredExecute()
            taskResult.await()

            val response = taskResult.getCompleted()?.result
            onPostExecute(response)
        }
    }

    /*
        coordinate preExecute,postExecute and doInBackground operations.
        This function can be consumed from Both Java and Kotlin source
    */
    fun execute() {
        var taskResult: TaskResult<TaskResponse?>?
        launchSequence {
            // any initialisation or pre requisite for the actual job about to start
            onPreExecute()
            //extract html page from jsonResponse
            taskResult = doInBackground()
            //update the UI
            onPostExecute(taskResult?.result)
        }
    }

    /*
      cancel the job if it is active
    */
    fun cancel() {
        if (job?.isActive == true) {
            job?.cancel()
            logJobStatus("cancel")
        } else {

        }
    }

    /*
      execute task as background.
      Do not call this method directly,let the framework execute it for you.
     */
    protected abstract suspend fun doInBackground(vararg args: Any): TaskResult<TaskResponse>

    /*
      execute UX updates. override this method if there is a need to update the UX.
      Do not call this method directly, let the framework execute it for you.
     */
    protected open fun onPostExecute(response: TaskResponse?) {
    }

    /*
       override this method if there is a need to perform any initialisation before the actual task
       Do not call this method directly,let the framework execute it for you.
     */

    protected open fun onPreExecute() {
    }

    /*
      override this method for report in between progress data to UI
    */
    protected open fun publishProgress() {
        //use a channel created from doInBackground
    }

    /*
       Returns a deferred Response.Override if there is a need to wait for the work to be completed.
    */
    protected open fun deferredExecute(): Deferred<TaskResult<TaskResponse?>?> {
        return coroutineScope.async {
            TaskResult(GenericResponse(""), true)
        }
    }

    /*
      execute UX updates in context of Main.
      By marking `block` as `suspend` this creates a suspend lambda which can call suspend functions.
    */
    fun updateUI(
        response: TaskResponse?,
        block: suspend (response: TaskResponse?) -> Unit
    ) {
        coroutineScope.launch {
            withContext(Dispatchers.Main) {
                block(response)
            }
        }

    }

    /*
       execute the provided block of code as launch...
       By marking `block` as `suspend` this creates a suspend lambda which can call suspend functions.
     */
    fun launchSequence(block: suspend () -> Unit): Job {
        return coroutineScope.launch {
            block()
        }
    }

    /*
       execute the provided block of code as async...
       By marking `block` as `suspend` this creates a suspend lambda which can call suspend functions.
     */
    fun asyncSequence(block: suspend () -> Unit): Job {
        return coroutineScope.async {
            block()
        }
    }

    /*
        helper for executing a runnable on android Main Looper (UI)
    */
    object RunnableDispatcher : CoroutineDispatcher() {
        override fun dispatch(
            context: CoroutineContext,
            block: Runnable
        ) {
            Handler(Looper.getMainLooper()).post {
                block.run()
            }
        }
    }

    /*
    helper for executing a runnable on a new thread
    */
    object RunnableSingleThreadDispatcher : CoroutineDispatcher() {
        override fun dispatch(
            context: CoroutineContext,
            block: Runnable
        ) {
            thread {
                block.run()
            }
        }
    }

    protected fun raiseJobAlreadyRunningException() {
        if (job?.isActive == true) {
            throw JobAlreadyCreatedException("Current job is active and cannot be re-used")
        } else {
            logJobStatus("raiseJobAlreadyRunningException")
        }
    }

    protected fun logJobStatus(source: String) {
        val active = coroutineScope.coroutineContext[Job]?.isActive;
        val cancelled = coroutineScope.coroutineContext[Job]?.isCancelled
        val completed = coroutineScope.coroutineContext[Job]?.isCompleted
    }
}