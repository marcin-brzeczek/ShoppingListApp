package mbitsystem.com.shopping.presentation.shoppingItem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import mbitsystem.com.shopping.databinding.FragmentAddShoppingItemBinding
import mbitsystem.com.shopping.presentation.base.BaseFragmentDialog
import mbitsystem.com.shopping.utils.getViewModel

class ShoppingItemFragment : BaseFragmentDialog() {

    private lateinit var binding: FragmentAddShoppingItemBinding

    internal val viewModel: ShoppingItemViewModel by lazy { getViewModel<ShoppingItemViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAddShoppingItemBinding.inflate(inflater, container, false).apply {
        binding = this
        viewModel = this@ShoppingItemFragment.viewModel
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.shouldDismiss.observe(this@ShoppingItemFragment, Observer { shouldDissmised ->
            if (shouldDissmised) {
                dismiss()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ShoppingItemFragmentArgs by navArgs()
        val shoppingItem = args.ShoppingItem
        viewModel.setModel(shoppingItem)

        binding.onClickAddShoppingList = {
            viewModel.save()
        }
        binding.onClickDissmisDialog = {
            dismiss()
        }
    }
}