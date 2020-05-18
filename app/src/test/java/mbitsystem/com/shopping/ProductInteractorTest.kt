package mbitsystem.com.shopping

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import mbitsystem.com.shopping.data.model.File
import mbitsystem.com.shopping.data.repository.FileRepository
import mbitsystem.com.shopping.state.FileState
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductInteractorTest : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var repository: FileRepository

    lateinit var underTest: FileInteractor

    @Before
    fun setUp() {
        underTest = FileInteractor(repository)
    }

    @Test
    fun `get files ascending`() {
        val files = dummyAllFiles
        Mockito.`when`(repository.getAllOrderByNameAscending()).thenReturn(Observable.just(files))

        val result = underTest.getFilesAsceding()

        val testObserver = TestObserver<FileState>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val fileState = testObserver.values()[0] as FileState.DataState
        val resultList = fileState.data
        assertThat(resultList.size, `is`(4))
        assertThat(resultList[0].filename, `is`("FileTest1"))
        assertThat(resultList[1].filename, `is`("FileTest2"))
    }

    private val dummyAllFiles: ArrayList<File>
        get() {
            val dummyFileList = ArrayList<File>()
            dummyFileList.add(File("1", "FileTest1", "urlPath", "", ""))
            dummyFileList.add(File("79", "FileTest2", "PosterPath2", "", ""))
            dummyFileList.add(File("987", "FileTest3", "PosterPath3", "", ""))
            dummyFileList.add(File("4223", "FileTest4", "PosterPath4", "", ""))
            return dummyFileList
        }
}