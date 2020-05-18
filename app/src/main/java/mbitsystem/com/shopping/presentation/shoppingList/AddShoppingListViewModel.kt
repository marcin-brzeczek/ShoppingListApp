package mbitsystem.com.shopping.presentation.shoppingList

import android.app.Application
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.subscribeBy
import mbitsystem.com.shopping.R
import mbitsystem.com.shopping.data.model.ShoppingList
import mbitsystem.com.shopping.data.repository.ShoppingListRepository
import mbitsystem.com.shopping.presentation.base.BaseViewModel
import mbitsystem.com.shopping.presentation.base.SingleLiveEvent
import mbitsystem.com.shopping.utils.SchedulerProviderDelegate
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AddShoppingListViewModel @Inject constructor(
    application: Application,
    private val repository: ShoppingListRepository,
    schedulerProviderDelegate: SchedulerProviderDelegate
) : BaseViewModel(application), SchedulerProviderDelegate by schedulerProviderDelegate {

    val shouldDismiss = SingleLiveEvent<Boolean>()
    val name = MutableLiveData<String>()
    var showError = MutableLiveData<Boolean>(false)
    val nameErrorText = MutableLiveData<String>()

    fun save() {
        if (validate()) {
            saveToDatabase()
        } else {
            showError.value = true
            nameErrorText.value = getString(R.string.error_name)
        }
    }

    private fun saveToDatabase() {
        repository.insert(ShoppingList(name = name.value!!, date = Date()))
            .connectIo()
            .subscribeBy(
                onComplete = { shouldDismiss.value = true },
                onError = {
                    showError.value = true
                    nameErrorText.value = "Error  Adding shopping list"
                    Timber.e(it)
                }
            )
            .addToDisposables()
    }

    private fun validate() = !name.value.isNullOrBlank()
}