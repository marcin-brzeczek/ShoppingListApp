package mbitsystem.com.shopping.presentation.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import mbitsystem.com.shopping.databinding.FragmentAddShoppingListBinding
import mbitsystem.com.shopping.presentation.base.BaseFragmentDialog
import mbitsystem.com.shopping.utils.getViewModel

class AddShoppingListFragment : BaseFragmentDialog() {

    private lateinit var binding: FragmentAddShoppingListBinding

    internal val viewModel: AddShoppingListViewModel by lazy { getViewModel<AddShoppingListViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddShoppingListBinding.inflate(inflater, container, false).apply {
        binding = this
        viewModel = this@AddShoppingListFragment.viewModel
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.shouldDismiss.observe(this@AddShoppingListFragment, Observer { shouldDissmised ->
            if (shouldDissmised) {
                dismiss()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.onClickAddShoppingList = {
            viewModel.save()
        }
        binding.onClickDissmisDialog = {
            dismiss()
        }
    }
}