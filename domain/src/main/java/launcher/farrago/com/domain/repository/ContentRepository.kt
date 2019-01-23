package launcher.farrago.com.domain.repository

import launcher.farrago.com.domain.exceptions.Failure
import launcher.farrago.com.domain.models.Content
import launcher.farrago.com.domain.usecase.Either

interface ContentRepository {
    suspend fun getContents(params: Any): Either<Failure, List<Content?>>
}