package launcher.farrago.com.domain.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving models to and from outer layers
 *
 * Usually Outer is the data remote repositories provide and Inner is how it needs to be presented for the Application
 * in terms of a model defined within the domain.
 *
 */
interface Mapper<Outer, Inner> {

    fun mapToInnerEntity(outerEntity: Outer): Inner

    fun mapToOuterEntity(innerEntity: Inner): Outer
}