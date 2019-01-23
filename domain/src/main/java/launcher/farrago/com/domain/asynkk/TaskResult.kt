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
package dp.com.asynckk

/*
References
- https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
- https://kotlinexpertise.com/kotlin-reified-types/
- https://proandroiddev.com/demystifying-kotlin-coroutines-6fe1f410570b
- https://proandroiddev.com/demystifying-coroutinecontext-1ce5b68407ad
- VNF
 */

open class TaskResult<out T>(
    val result: T?,
    val success: Boolean
) {

    fun isSuccessful(): Boolean {
        return success
    }

    override fun toString(): String {
        return result.toString()
    }
}


