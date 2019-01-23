package launcher.farrago.com.data.repository

import launcher.farrago.com.domain.datastore.ContentDataStore
import launcher.farrago.com.domain.exceptions.Failure
import launcher.farrago.com.domain.models.Content
import launcher.farrago.com.domain.repository.ContentRepository
import launcher.farrago.com.domain.usecase.Either

class OMDBContentRepository (private val dataStore:ContentDataStore) : ContentRepository{
    override suspend fun getContents(params: Any): Either<Failure, List<Content?>> {
        var contents = emptyList<Content?>()
        contents = dataStore.getContents(params as Map<String, String>)
        return Either.Right(contents)

    }
}
