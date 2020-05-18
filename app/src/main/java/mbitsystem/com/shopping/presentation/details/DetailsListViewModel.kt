package mbitsystem.com.shopping.presentation.details

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import mbitsystem.com.shopping.BR
import mbitsystem.com.shopping.R
import mbitsystem.com.shopping.data.model.ShoppingItem
import mbitsystem.com.shopping.data.repository.ShoppingItemRepository
import mbitsystem.com.shopping.presentation.base.BaseViewModel
import mbitsystem.com.shopping.utils.SchedulerProviderDelegate
import me.tatarka.bindingcollectionadapter2.OnItemBind
import timber.log.Timber
import javax.inject.Inject

class DetailsListViewModel @Inject constructor(
    application: Application,
    val diff: ShoppingItemDiffCallback,
    private val shoppingItemRepository: ShoppingItemRepository,
    schedulerProviderDelegate: SchedulerProviderDelegate
) : BaseViewModel(application), SchedulerProviderDelegate by schedulerProviderDelegate {

    val header = MutableLiveData<String>()
    val shoppingItems = MutableLiveData<List<ShoppingItem>>()

    val itemBinding = OnItemBind<ShoppingItem> { itemBinding, _, item ->
        itemBinding.set(BR.model, R.layout.shopping_item)
        itemBinding.bindExtra(BR.listener, this)
    }

    fun deleteItem(shoppingListId: Long) {
        shoppingItemRepository.delete(shoppingListId)
            .connectIo()
            .subscribeBy(
                onComplete = { Unit },
                onError = {
                    Timber.e("Failed delete shopping item: $it")
                }
            )
            .addToDisposables()
    }

    fun getShoppingItems(shoppingListId: Long) {
        shoppingItemRepository.getAllOrderByDate(shoppingListId)
            .connectIo()
            .subscribeBy(
                onNext = { shoppingItems.value = it },
                onError = { Timber.e("Error fetching shopping items: $it") }
            )
            .addToDisposables()
    }
}