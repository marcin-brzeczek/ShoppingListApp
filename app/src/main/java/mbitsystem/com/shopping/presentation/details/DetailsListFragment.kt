package mbitsystem.com.shopping.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import mbitsystem.com.shopping.data.model.ShoppingItem
import mbitsystem.com.shopping.databinding.FragmentDetailsListBinding
import mbitsystem.com.shopping.presentation.base.BaseFragment
import mbitsystem.com.shopping.utils.*

class DetailsListFragment : BaseFragment(), OnSwipeListener {

    lateinit var binding: FragmentDetailsListBinding

    private val viewModel by lazy { getViewModel<DetailsListViewModel>() }

    private val itemTouchHelper by lazy {
        ItemTouchHelper(
            ItemSwipeHelper(
                requireContext(), this
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsListBinding.inflate(inflater, container, false).apply {
        binding = this
        viewModel = this@DetailsListFragment.viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        val args: DetailsListFragmentArgs by navArgs()
        val shoppingList = args.ShoppingListItem
        viewModel.header.value = shoppingList.name
        viewModel.getShoppingItems(shoppingList.shoppingListId)

        binding.onClickNewList = {
            val newShoppingItem = ShoppingItem(shoppingListId = shoppingList.shoppingListId, name = EMPTY_STRING)
            navController.navigate(DetailsListFragmentDirections.actionShoppingItem(newShoppingItem))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun delete(position: Int) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        val shoppingItemId = getShoppingItemFromPosition(position)
        shoppingItemId?.let {
            viewModel.deleteItem(it.shoppingItemId)
        }
    }

    override fun edit(position: Int) {
        binding.recyclerView.adapter?.notifyDataSetChanged()
        val shoppingItemId = getShoppingItemFromPosition(position)
        shoppingItemId?.let {
            navController.navigate(DetailsListFragmentDirections.actionShoppingItem(it))
        }
    }

    private fun getShoppingItemFromPosition(position: Int) =
        binding.recyclerView.getAdapterItem<ShoppingItem>(position)
}
