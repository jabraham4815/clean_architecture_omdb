package launcher.farrago.com.data.mappers

import launcher.farrago.com.data.models.OMDBItem
import launcher.farrago.com.domain.mapper.Mapper
import launcher.farrago.com.domain.models.Content

internal object OMDBItemToContentMapper : Mapper<OMDBItem, Content> {
    override fun mapToInnerEntity(outerEntity: OMDBItem): Content {
        return outerEntity.let { it: OMDBItem ->
            val ct = Content()
            ct.genres = it.Type
            ct.imdbID = it.imdbID
            ct.titleMovie = it.Title
            ct.urlPoster = it.Poster
            ct.yearReleased = it.Year
            ct
        }
    }

    override fun mapToOuterEntity(innerEntity: Content): OMDBItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
