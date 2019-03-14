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
class DataModule {

    @Provides
    @Singleton
    fun provideOMDBDataStore(): OMDBContentDataStore {
        Log.d("OMDB", "Providing provideOMDBDataStore")
        return OMDBContentDataStore()
    }

    @Provides
    @Singleton
    fun provideCacheOMDBDataStore(): CachedContentDataStore {
        Log.d("OMDB", "providing CacheOMDBDataStore")
        return CachedContentDataStore()
    }

    @Provides
    @Singleton
    @Inject
    fun provideOMDBDataRepository(dataStore: OMDBContentDataStore): OMDBContentRepository {
        Log.d("OMDB", "providing OMDBDataRepository")
        return OMDBContentRepository(dataStore)
    }

    @Provides
    @Singleton
    @Inject
    fun provideOMDBSearchUseCase(repository: OMDBContentRepository): GetContentsUseCase<Map<String, String>> {
        Log.d("OMDB", "providing OMDBSearchUseCase")
        return GetContentsUseCase<Map<String, String>>(repository)
    }
}