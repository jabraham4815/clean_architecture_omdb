
package launcher.farrago.com.domain.models

/*
   This is how a Content < Movie , Music , TV > is represented within the Domain
   Json listed here is how Application Receive the data say Outer and a proper Mapper
   implementation will be deployed to convert to inner i.e Content class.

   {
  "Search": [
    {
      "Title": "Hello, My Name Is Doris",
      "Year": "2015",
      "imdbID": "tt3766394",
      "Type": "movie",
      "Poster": "https://m.media-amazon.com/images/M/MV5BMTg0NTM3MTI1MF5BMl5BanBnXkFtZTgwMTAzNTAzNzE@._V1_SX300.jpg"
    }
  ],
  "totalResults”:”1”,”Response": "True"
}

 */
open class Content {
    var urlPoster: String? = null
        get() = field
        set(value) {
            field = value
        }

    var titleMovie: String? = null
        get() = field
        set(value) {
            field = value
        }

    var genres: String? = null
        get() = field
        set(value) {
            field = value
        }

    var yearReleased: String? = null
        get() = field
        set(value) {
            field = value
        }

    var imdbID: String? = null
        get() = field
        set(value) {
            field = value
        }

}
