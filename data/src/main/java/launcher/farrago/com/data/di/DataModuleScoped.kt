package launcher.farrago.com.data.di

import android.util.Log
import dagger.Module
import dagger.Provides
import launcher.farrago.com.data.datastore.CachedContentDataStore
import launcher.farrago.com.data.datastore.OMDBContentDataStore
import launcher.farrago.com.data.repository.OMDBContentRepository
import launcher.farrago.com.data.usecases.GetContentsUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DataModuleScoped {

    @Provides
    @Singleton
    fun provideOMDBDataStore(): OMDBContentDataStore {
        Log.d("DaggerOMDB", "Enter provideOMDBDataStore")
        return OMDBContentDataStore()
    }

    @Provides
    @Singleton
    fun provideCacheOMDBDataStore(): CachedContentDataStore {
        Log.d("DaggerOMDB", "Enter provideCacheOMDBDataStore")
        return CachedContentDataStore()
    }

    @Provides
    @Singleton
    @Inject
    fun provideOMDBDataRepository(dataStore: OMDBContentDataStore): OMDBContentRepository {
        Log.d("DaggerOMDB", "Enter provideOMDBDataRepository")
        return OMDBContentRepository(dataStore)
    }

    @Provides
    @Singleton
    @Inject
    fun provideOMDBSearchUseCase(repository: OMDBContentRepository): GetContentsUseCase<Map<String, String>> {
        Log.d("DaggerOMDB", "Enter provideOMDBSearchUseCase")
        return GetContentsUseCase<Map<String, String>>(repository)
    }
}