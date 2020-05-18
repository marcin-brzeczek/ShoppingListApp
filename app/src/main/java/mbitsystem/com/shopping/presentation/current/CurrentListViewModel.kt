package mbitsystem.com.shopping.presentation.current

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import mbitsystem.com.shopping.BR
import mbitsystem.com.shopping.R
import mbitsystem.com.shopping.data.model.ItemState
import mbitsystem.com.shopping.data.model.ShoppingList
import mbitsystem.com.shopping.data.repository.ShoppingListRepository
import mbitsystem.com.shopping.presentation.base.BaseViewModel
import mbitsystem.com.shopping.presentation.base.SingleLiveEvent
import mbitsystem.com.shopping.utils.SchedulerProviderDelegate
import me.tatarka.bindingcollectionadapter2.OnItemBind
import timber.log.Timber
import javax.inject.Inject

class CurrentListViewModel @Inject constructor(
    application: Application,
    private val shoppingListRepository: ShoppingListRepository,
    schedulerProviderDelegate: SchedulerProviderDelegate
) : BaseViewModel(application), SchedulerProviderDelegate by schedulerProviderDelegate,
        (ItemState, ShoppingList) -> Unit {

    val shoppingLists = MutableLiveData<List<ShoppingList>>()

    internal val onClickItem = SingleLiveEvent<Pair<ShoppingList, ItemState>>()

    val itemBinding = OnItemBind<ShoppingList> { itemBinding, _, item ->
        itemBinding.set(BR.model, R.layout.shopping_list_item)
        itemBinding.bindExtra(BR.listener, this)
    }

    override fun invoke(state: ItemState, model: ShoppingList) {
        onClickItem.value = Pair(model, state)
    }

    fun addToArchive(shoppingListId: Long) {
        shoppingListRepository.addToArchive(shoppingListId)
            .connectIo()
            .subscribeBy(
                onComplete = { Unit },
                onError = { Timber.e("Error adding to archive shopping list: $it") }
            )
            .addToDisposables()
    }

    fun getShoppingList() {
        shoppingListRepository.getAllOrderByDate()
            .connectIo()
            .subscribeBy(
                onNext = { shoppingLists.value = it },
                onError = { Timber.e("Error fetching shopping list: $it") }
            )
            .addToDisposables()
    }

}