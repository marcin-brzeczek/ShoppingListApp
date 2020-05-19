package mbitsystem.com.shopping.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun errorText(textInputLayout: TextInputLayout, errorMessage: String?) {
    textInputLayout.apply {
        isErrorEnabled = errorMessage != null
        errorMessage?.let { error = it }
    }
}

@BindingAdapter("visibleOrGone")
fun visibleOrGone(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("onLongClick")
fun onLongClick(view: View, doOnClick: () -> Unit) {
    view.setOnLongClickListener {
        doOnClick()
        true
    }
}

@BindingAdapter("app:cursorPositionEnd")
fun setCursorPositionEnd(view:TextInputEditText, value:Boolean) {
    view.text?.let {
        if (value && it.toString().isNotEmpty())
            view.setSelection(view.text.toString().length)
    }
}