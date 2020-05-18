package mbitsystem.com.shopping.presentation.shoppingItem

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import mbitsystem.com.shopping.R
import mbitsystem.com.shopping.data.model.ShoppingItem
import mbitsystem.com.shopping.data.repository.ShoppingItemRepository
import mbitsystem.com.shopping.presentation.base.BaseViewModel
import mbitsystem.com.shopping.presentation.base.SingleLiveEvent
import mbitsystem.com.shopping.utils.SchedulerProviderDelegate
import timber.log.Timber
import javax.inject.Inject

class ShoppingItemViewModel @Inject constructor(
    application: Application,
    private val repository: ShoppingItemRepository,
    schedulerProviderDelegate: SchedulerProviderDelegate
) : BaseViewModel(application), SchedulerProviderDelegate by schedulerProviderDelegate {

    val shoppingItem = MutableLiveData<ShoppingItem>()
    val shouldDismiss = SingleLiveEvent<Boolean>()
    val name = MutableLiveData<String>()
    var showError = MutableLiveData<Boolean>(false)
    val nameErrorText = MutableLiveData<String>()
    val buttonLabel = MutableLiveData<String>(getString(R.string.update_shopping_item))
    var shouldUpdate = true

    fun setModel(shoppingItem: ShoppingItem){
        this.shoppingItem.value = shoppingItem
        if (shoppingItem.name.isBlank()) {
            buttonLabel.value = getString(R.string.add_shopping_item)
            shouldUpdate = false
        }else{
            name.value = shoppingItem.name
        }
    }

    fun save() {
        if (validate()) {
            if (shouldUpdate) {
                updateItem()
            } else {
                saveItem()
            }
        } else {
            showError.value = true
            nameErrorText.value = getString(R.string.error_name)
        }
    }

    private fun saveItem() {
        repository.insert(
            ShoppingItem(
                name = name.value!!,
                shoppingListId = shoppingItem.value!!.shoppingListId
            )
        )
            .connectIo()
            .subscribeBy(
                onComplete = { shouldDismiss.value = true },
                onError = {
                    showError.value = true
                    nameErrorText.value = "Error Adding shopping item"
                    Timber.e(it)
                }
            )
            .addToDisposables()
    }

    private fun updateItem() {
        repository.update(name = name.value!!, shoppingItemId = shoppingItem.value!!.shoppingItemId)
            .connectIo()
            .subscribeBy(
                onComplete = { shouldDismiss.value = true },
                onError = {
                    showError.value = true
                    nameErrorText.value = "Error Updating shopping item"
                    Timber.e(it)
                }
            )
            .addToDisposables()
    }

    private fun validate() = !name.value.isNullOrBlank()
}