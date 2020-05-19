package mbitsystem.com.shopping

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import mbitsystem.com.shopping.data.dao.ShoppingItemDao
import mbitsystem.com.shopping.data.model.ShoppingItem
import mbitsystem.com.shopping.data.repository.ShoppingItemRepository
import mbitsystem.com.shopping.presentation.details.DetailsListViewModel
import mbitsystem.com.shopping.presentation.details.ShoppingItemDiffCallback
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Unit test for [DetailsListViewModelTest]
 */
class DetailsListViewModelTest : BaseTest() {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var dao: ShoppingItemDao

    @Mock
    lateinit var application: Application

    @Mock
    lateinit var diff: ShoppingItemDiffCallback

    @Mock
    lateinit var observer: Observer<List<ShoppingItem>>

    @Mock
    lateinit var lifecycleOwner: LifecycleOwner

    lateinit var lifecycle: Lifecycle
    lateinit var viewModel: DetailsListViewModel
    lateinit var schedulerProvider: TestSchedulerProvider
    lateinit var testScheduler: TestScheduler
    lateinit var repository: ShoppingItemRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        lifecycle = LifecycleRegistry(lifecycleOwner)
        testScheduler = TestScheduler()
        schedulerProvider = TestSchedulerProvider(testScheduler)
        repository = ShoppingItemRepository(dao)
        viewModel = DetailsListViewModel(application, diff, repository, schedulerProvider)
        viewModel.shoppingItems.observeForever(observer)
    }

    @Test
    fun testNull() {
        assertNotNull(application)
        assertNotNull(diff)
        assertNotNull(schedulerProvider)
        assertNotNull(repository)
        assertNotNull(viewModel)
        assertTrue(viewModel.shoppingItems.hasObservers())
    }

    @Test
    fun `verify live data when get shopping items`() {

        //Given
        val requiredShoppingList = SHOPPPING_ITEMS

        //When
        `when`(repository.getAllOrderByDate(SHOPPING_LIST_ID)).thenReturn(
            Observable.just(
                SHOPPPING_ITEMS
            )
        )
        viewModel.getShoppingItems(SHOPPING_LIST_ID)
        testScheduler.triggerActions()

        //Then
        verify(observer).onChanged(SHOPPPING_ITEMS)
        assertThat(viewModel.shoppingItems.value?.size, `is`(requiredShoppingList.size))
        assertThat(
            viewModel.shoppingItems.value?.first()?.name,
            `is`(requiredShoppingList.first().name)
        )
    }

    @Test
    fun `verify live data when error occurs get shopping items`() {

        //Given
        val testError = "error"
        val observable: Observable<List<ShoppingItem>> = Observable.create { emitter ->
            emitter.onError(Exception(testError))
        }

        //When
        `when`(repository.getAllOrderByDate(SHOPPING_LIST_ID)).thenReturn(observable)
        viewModel.getShoppingItems(SHOPPING_LIST_ID)
        testScheduler.triggerActions()

        // Then
        verifyNoMoreInteractions(observer)
        assertTrue(viewModel.shoppingItems.value.isNullOrEmpty())
    }

    companion object {
        private const val SHOPPING_LIST_ID = 218738237L
        private val SHOPPPING_ITEMS = listOf(
            ShoppingItem(111, SHOPPING_LIST_ID, "Apple"),
            ShoppingItem(1234, SHOPPING_LIST_ID, "Bread")
        )
    }
}