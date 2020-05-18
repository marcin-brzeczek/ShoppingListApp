package mbitsystem.com.shopping.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import mbitsystem.com.shopping.data.dao.ShoppingListDao
import mbitsystem.com.shopping.data.model.ShoppingList
import javax.inject.Inject

 class ShoppingListRepository @Inject constructor(val shoppingListDao: ShoppingListDao) {

    fun getAllOrderByDate(): Observable<List<ShoppingList>> = shoppingListDao.getAllCurrentOrderByDate()

    fun getAllArchivedOrderByDate(): Observable<List<ShoppingList>> = shoppingListDao.getAllArchivedOrderByDate()

    fun delete(shoppingList: ShoppingList): Completable = shoppingListDao.delete(shoppingList)

    fun insert(shoppingList: ShoppingList) = shoppingListDao.insert(shoppingList)

     fun addToArchive(shoppingListId:Long) = shoppingListDao.addToArchive(shoppingListId)
}