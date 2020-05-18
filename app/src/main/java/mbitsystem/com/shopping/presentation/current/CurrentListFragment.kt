package mbitsystem.com.shopping.presentation.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import mbitsystem.com.shopping.R
import mbitsystem.com.shopping.data.model.ItemState
import mbitsystem.com.shopping.presentation.base.BaseFragment
import mbitsystem.com.shopping.databinding.FragmentCurrentListBinding
import mbitsystem.com.shopping.utils.getViewModel

class CurrentListFragment : BaseFragment(){

    lateinit var binding: FragmentCurrentListBinding

    private val viewModel by lazy { getViewModel<CurrentListViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCurrentListBinding.inflate(inflater, container, false).apply {
        binding = this
        viewModel = this@CurrentListFragment.viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getShoppingList()
        binding.onClickNewList = {
            navController.navigate(CurrentListFragmentDirections.actionNewShoppingList())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.onClickItem.observe(this@CurrentListFragment, Observer {
            val state = it.second
            val shoppingListId = it.first.shoppingListId
            when(state){
                ItemState.DETAILS -> navController.navigate(CurrentListFragmentDirections.actionDetails(it.first))
                ItemState.ARCHIVE -> showInformationDialog(
                    getString(R.string.add_to_archive),
                    getString(R.string.want_add_to_archive, it.first.name),
                    { viewModel.addToArchive(shoppingListId)})
            }
        })
    }
}