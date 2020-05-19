package mbitsystem.com.shopping.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import mbitsystem.com.shopping.data.model.ShoppingItem

@Dao
interface ShoppingItemDao {

    @Query("select * from shoppingItems where shoppingListId = :shoppingListCreatorId order by shoppingItemId desc")
    fun getAllOrderByDate(shoppingListCreatorId: Long): Observable<List<ShoppingItem>>

    @Query("select * from shoppingItems where  shoppingItemId =:shoppingItemId")
    fun getById(shoppingItemId: Long): Single<ShoppingItem>

    @Query("UPDATE shoppingItems SET name =:name WHERE shoppingItemId =:shoppingItemId")
    fun update(shoppingItemId: Long, name: String): Completable

    @Query("DELETE FROM shoppingItems WHERE shoppingItemId = :shoppingItemId")
    fun delete(shoppingItemId: Long): Completable

    @Query("DELETE FROM shoppingItems")
    fun deleteAll()

    @Insert
    fun insert(shoppingItem: ShoppingItem): Completable
}