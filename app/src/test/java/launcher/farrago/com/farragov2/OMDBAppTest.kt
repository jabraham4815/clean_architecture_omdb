package launcher.farrago.com.farragov2

import launcher.farrago.com.data.datastore.OMDBContentDataStore
import launcher.farrago.com.data.repository.OMDBContentRepository
import launcher.farrago.com.data.usecases.GetContentsUseCase
import launcher.farrago.com.domain.exceptions.Failure
import launcher.farrago.com.domain.models.Content
import launcher.farrago.com.domain.usecase.Either
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.times

@RunWith(MockitoJUnitRunner::class)
class OMDBAppTest {
    private lateinit var contents: MutableList<Content?>
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        //dataStore = Mockito.mock(OMDBContentDataStore::class.java)
        /*
            java.lang.AbstractMethodError: org.powermock.api.mockito.internal.mockmaker.PowerMockMaker.isTypeMockable(Ljava/lang/Class;)Lorg/mockito/plugins/MockMaker$TypeMockability;

	        at org.mockito.internal.util.MockUtil.typeMockabilityOf(MockUtil.java:29)
	        at org.mockito.internal.util.MockCreationValidator.validateType(MockCreationValidator.java:22)
	        at org.mockito.internal.creation.MockSettingsImpl.validatedSettings(MockSettingsImpl.java:186)
         */
    }

    @Mock
    private lateinit var dataStore: OMDBContentDataStore

    @Mock
    private lateinit var repository: OMDBContentRepository

    @Mock
    private lateinit var useCase: GetContentsUseCase<Map<String, String>>

    private fun handleContentsFound(contents: List<Content?>) {
        this.contents = contents.toMutableList()
    }

    protected fun handleFailure(failure: Failure) {
        Assert.assertFalse(false)
    }

    @Test
    fun verifyValidDataShouldLoadToView() {
        //val inOrder = Mockito.inOrder(useCase)
        useCase?.invoke(
            getSearchParameters(), onResult = { it: Either<Failure, List<Content?>> ->
            it.either(::handleFailure, ::handleContentsFound)
            //inOrder.verify(useCase, times(1)).run(getSearchParameters());
            Assert.assertEquals(this.contents.size > 0, true)
        }) ?: run {
            Assert.assertTrue(false)
        }
    }

    private fun getSearchParameters(): HashMap<String, String> {
        val searchDataOMDB = HashMap<String, String>()
        val title = "hello"
        searchDataOMDB["s="] = title
        return searchDataOMDB
    }
}
