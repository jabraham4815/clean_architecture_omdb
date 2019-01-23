package launcher.farrago.com.data.datastore

import android.util.Log
import kotlinx.coroutines.suspendCancellableCoroutine
import launcher.farrago.com.data.api.omdb.rest.OMDBRest
import launcher.farrago.com.data.mappers.OMDBItemToContentMapper
import launcher.farrago.com.data.models.OMDBItem
import launcher.farrago.com.data.models.OMDBItems
import launcher.farrago.com.domain.datastore.ContentDataStore
import launcher.farrago.com.domain.models.Content
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

class OMDBContentDataStore : ContentDataStore {
    override suspend fun getContents(inputItems: Map<String, String>): List<Content?> {
        return suspendCancellableCoroutine { continuation ->
            /**
             * Step 1 Execute endpoint
             */
            val OMDBContents: MutableList<OMDBItem?> = mutableListOf()
            val contents: MutableList<Content?> = mutableListOf()

            val searchDataOMDB = HashMap<String, String>()
            searchDataOMDB.put("s=", "hello")
            //val loadContentsCall = OMDBRest.INSTANCE.getCaller()?.loadContent2("hello")
            val loadContentsCall = OMDBRest.INSTANCE.getCaller()?.loadContent(inputItems)
            loadContentsCall?.enqueue(object : Callback<OMDBItems> {
                override fun onResponse(
                    call: Call<OMDBItems>,
                    response: Response<OMDBItems>?
                ) {
                    response?.body()?.let {
                        if (it.Search != null) {
                            OMDBContents.addAll(it.Search)
                        }
                    }
                    /**
                     * Step 2 Execute domain mapping
                     */

                    OMDBContents.map {
                        if (it != null) {
                            val c = OMDBItemToContentMapper.mapToInnerEntity(it)
                            contents.add(c)
                        }
                    }
                    continuation.resume(contents)
                    Log.d("Farrago", "response from OMDB Rcvd... count " + OMDBContents.size)

                }

                override fun onFailure(
                    call: Call<OMDBItems>,
                    t: Throwable
                ) {
                    continuation.resume(emptyList())
                    Log.d("Farrago", "Error response from OMDB Rcvd... ")
                }
            })
        }
    }
}
