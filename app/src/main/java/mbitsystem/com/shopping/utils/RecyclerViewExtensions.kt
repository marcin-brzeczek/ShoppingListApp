package mbitsystem.com.shopping.utils

import androidx.recyclerview.widget.RecyclerView
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
fun <T> RecyclerView.getAdapterItem(position: Int) =
    (this.adapter as? BindingRecyclerViewAdapter<*>)?.getAdapterItem(position) as? T