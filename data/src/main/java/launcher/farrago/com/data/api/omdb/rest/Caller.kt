package launcher.farrago.com.data.api.omdb.rest

import launcher.farrago.com.data.models.OMDBItems
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface Caller {
    @GET("?")
    fun loadContent(
        @QueryMap options: Map<String, String>
    ): Call<OMDBItems>

    /*
       ToDo should be removed after fixing encoding problem
     */
    @GET("?")
    fun loadContent2(@Query("s=",encoded = false) name: String): Call<OMDBItems>
}
