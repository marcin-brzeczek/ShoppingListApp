package mbitsystem.com.shopping.presentation.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import mbitsystem.com.shopping.InitApp

abstract class BaseViewModel(application: Application) :  AndroidViewModel(application) {

    private val disposables = CompositeDisposable()

    protected fun getString(stringResource: Int) = getApplication<InitApp>().getString(stringResource)

    protected fun Disposable.addToDisposables() {
        disposables.add(this)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}