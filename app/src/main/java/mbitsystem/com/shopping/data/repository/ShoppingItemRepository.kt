package mbitsystem.com.shopping.data.repository

import io.reactivex.Completable
import mbitsystem.com.shopping.data.dao.ShoppingItemDao
import mbitsystem.com.shopping.data.model.ShoppingItem
import javax.inject.Inject

open class ShoppingItemRepository @Inject constructor(val shoppingItemDao: ShoppingItemDao) {

    fun getAllOrderByDate(shoppingListId: Long) = shoppingItemDao.getAllOrderByDate(shoppingListId)

    fun delete(shoppingItemId: Long): Completable = shoppingItemDao.delete(shoppingItemId)

    fun update(shoppingItemId: Long, name:String) = shoppingItemDao.update(shoppingItemId, name)

    fun insert(shoppingList: ShoppingItem) = shoppingItemDao.insert(shoppingList)
}