package launcher.farrago.com.data.usecases

import android.util.Log
import launcher.farrago.com.domain.exceptions.Failure
import launcher.farrago.com.domain.models.Content
import launcher.farrago.com.domain.repository.ContentRepository
import launcher.farrago.com.domain.usecase.UseCase
import launcher.farrago.com.domain.usecase.Either

class GetContentsUseCase<PARAMS>(private val contentsRepository: ContentRepository) : UseCase<PARAMS, List<Content?>>() {
    override suspend fun run(params: PARAMS?): Either<Failure, List<Content?>> {
        Log.d("USECASE","GetContentsUseCase.run Enter")
        if (params != null) {
            Log.d("USECASE","GetContentsUseCase.run Exit")
            return contentsRepository.getContents(params as Map<*, *>)

        }
        Log.d("USECASE","GetContentsUseCase.run Exit")
        return Either.Left(Failure.ArgumentError())
    }

}