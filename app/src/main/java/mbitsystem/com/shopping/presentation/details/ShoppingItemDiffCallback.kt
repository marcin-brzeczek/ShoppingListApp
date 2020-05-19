package mbitsystem.com.shopping.presentation.details

import androidx.recyclerview.widget.DiffUtil
import mbitsystem.com.shopping.data.model.ShoppingItem
import javax.inject.Inject

open class ShoppingItemDiffCallback @Inject constructor()  : DiffUtil.ItemCallback<ShoppingItem>(){
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem) =
        oldItem.shoppingItemId == newItem.shoppingItemId

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem) =
        oldItem.name == newItem.name
}