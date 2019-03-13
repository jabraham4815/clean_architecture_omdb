package launcher.farrago.com.data.api.omdb.rest

import launcher.farrago.com.core.di.CoreComponent
import launcher.farrago.com.core.di.DaggerCoreComponent

class  OMDBRest private constructor(){
    private var caller: Caller? = null
    private lateinit var coreComponent: CoreComponent

    object Holder {
        val instance = OMDBRest()
    }
    companion object {
        val INSTANCE: OMDBRest by lazy { Holder.instance }
    }

    init {
        createCoreComponent()
    }

    internal fun getCaller(): Caller? {
        val retrofit =coreComponent.getRetrofit()
        if (caller == null){
            caller = retrofit.create(Caller::class.java)
        }
        return caller
    }

    internal fun getCoreComponent():CoreComponent{
        return coreComponent
    }

    private fun createCoreComponent() {
        if (!this::coreComponent.isInitialized) {
            coreComponent = DaggerCoreComponent
                .builder()
                .build()
        }
    }
}



