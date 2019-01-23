package launcher.farrago.com.data.datastore

import launcher.farrago.com.domain.datastore.ContentDataStore
import launcher.farrago.com.domain.models.Content

class CachedContentDataStore : ContentDataStore {
    /**
     * This one should fetch data from the local cache such as room
     */
    override suspend fun getContents(inputItems: Map<String, String>): List<Content?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}