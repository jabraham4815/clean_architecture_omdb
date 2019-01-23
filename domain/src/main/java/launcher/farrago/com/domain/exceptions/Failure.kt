package launcher.farrago.com.domain.exceptions
/*
 for handling fail path for Either
 */
sealed class Failure {
    //specify domain specific failures
    class ArgumentError: Failure()
    //extend this class for feature specific failures
    abstract class FeatureFailure : Failure()
}