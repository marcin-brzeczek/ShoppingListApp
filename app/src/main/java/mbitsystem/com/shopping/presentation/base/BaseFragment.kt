package mbitsystem.com.shopping.presentation.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.android.support.DaggerFragment
import mbitsystem.com.shopping.R

abstract class BaseFragment : DaggerFragment() {

    protected lateinit var navController: NavController

    private val parentActivity
        get() = (activity as? BaseActivity) ?: throw IllegalAccessError("Can't access to parent activity")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    fun showInformationDialog(title: String, message: String, doOnClick: () -> Unit = {}): AlertDialog =
        AlertDialog.Builder(requireContext(), R.style.AlertDialog)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(requireContext().getString(R.string.ok), { dialog, which -> doOnClick() })
            .setNegativeButton(requireContext().getString(R.string.cancel), {dialog, which ->  dialog.cancel() })
            .show()
}