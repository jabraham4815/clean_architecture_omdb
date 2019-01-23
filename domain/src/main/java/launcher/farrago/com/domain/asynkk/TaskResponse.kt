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

/**
 * Domain specific Tasks should be listed here.
 */
sealed class TaskResponse(
    val Host: String? = null,
    val Elapsed: Double? = null,
    val ElapsedWithoutJsonDeserialization: Double? = null,
    val ExpiresMsSinceEpoch: Long? = null
)
data class GenericResponse(val value: Any?) : TaskResponse()
class JobAlreadyCreatedException (val error:String?):RuntimeException(error)

/**
 * Extended tasks listed here
 */
data class OMDBContentResponse(val value: String?) : TaskResponse()


