package mbitsystem.com.shopping.presentation.archived

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import mbitsystem.com.shopping.BR
import mbitsystem.com.shopping.R
import mbitsystem.com.shopping.data.model.ShoppingList
import mbitsystem.com.shopping.data.repository.ShoppingListRepository
import mbitsystem.com.shopping.presentation.base.BaseViewModel
import mbitsystem.com.shopping.utils.SchedulerProviderDelegate
import me.tatarka.bindingcollectionadapter2.OnItemBind
import timber.log.Timber
import javax.inject.Inject

class ArchivedListViewModel @Inject constructor(
    application: Application,
    private val shoppingListRepository: ShoppingListRepository,
    schedulerProviderDelegate: SchedulerProviderDelegate
) : BaseViewModel(application), SchedulerProviderDelegate by schedulerProviderDelegate {

    val shoppingLists = MutableLiveData<List<ShoppingList>>()

    val itemBinding = OnItemBind<ShoppingList> { itemBinding, _, item ->
        itemBinding.set(BR.model, R.layout.shopping_list_item)
    }

    fun getArchivedShoppingList() {
        shoppingListRepository.getAllArchivedOrderByDate()
            .connectIo()
            .subscribeBy(
                onNext = { shoppingLists.value = it },
                onError = { Timber.e("Error fetching shopping list: $it") }
            )
            .addToDisposables()
    }
}