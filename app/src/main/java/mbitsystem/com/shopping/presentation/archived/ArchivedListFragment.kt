package mbitsystem.com.shopping.presentation.archived

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mbitsystem.com.shopping.databinding.FragmentArchivedListBinding
import mbitsystem.com.shopping.presentation.base.BaseFragment
import mbitsystem.com.shopping.utils.getViewModel

class ArchivedListFragment : BaseFragment() {

    lateinit var binding: FragmentArchivedListBinding

    private val viewModel by lazy { getViewModel<ArchivedListViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentArchivedListBinding.inflate(inflater, container, false).apply {
        binding = this
        viewModel = this@ArchivedListFragment.viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArchivedShoppingList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}