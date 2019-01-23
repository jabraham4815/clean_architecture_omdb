
package launcher.farrago.com.domain.datastore

import launcher.farrago.com.domain.models.Content

interface ContentDataStore {
    /**
     *  inputItems are the data required to build the query for getting contents from repository
     */
    suspend fun getContents(inputItems: Map<String, String>): List<Content?>
}
