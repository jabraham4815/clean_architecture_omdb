package launcher.farrago.com.data.api.omdb.rest

//import launcher.farrago.com.core.Rest.HttpManager

class  OMDBRest private constructor(){
    object Holder {
        val instance = OMDBRest()
    }
    companion object {
        val INSTANCE: OMDBRest by lazy { Holder.instance }
    }

    private var caller: Caller? = null

    fun getCaller(): Caller? {
        if (caller == null){
            caller = HttpManager.instance.retrofit?.create(Caller::class.java)
        }
        return caller
    }
}



