package mbitsystem.com.shopping.utils

import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import mbitsystem.com.shopping.presentation.base.BaseActivity

inline fun <reified T : ViewModel> Fragment.getViewModel(body: T.() -> Unit = {}): T {
    val viewModelFactory = (requireActivity() as BaseActivity).viewModelFactory
    val viewModel = ViewModelProviders.of(this, viewModelFactory)[T::class.java]
    viewModel.body()
    return viewModel
}

fun Fragment.hideKeyboard() {
    val inputManager = this.context?.getSystemService<InputMethodManager>()
    view?.windowToken?.run {
        inputManager?.hideSoftInputFromWindow(this, 0)
    }
}

fun Fragment.showShortToast(message:String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()