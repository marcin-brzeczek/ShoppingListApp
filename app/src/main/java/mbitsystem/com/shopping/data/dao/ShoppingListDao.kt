package mbitsystem.com.shopping.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import mbitsystem.com.shopping.data.model.ShoppingList

@Dao
interface ShoppingListDao {

    @Query("select * from shoppingLists where archived =:archived order by date desc")
    fun getAllCurrentOrderByDate(archived: Boolean = false): Observable<List<ShoppingList>>

    @Query("select * from shoppingLists where archived =:archived order by date desc")
    fun getAllArchivedOrderByDate(archived: Boolean = true): Observable<List<ShoppingList>>

    @Delete
    fun delete(shoppingList: ShoppingList): Completable

    @Insert
    fun insert(shoppingList: ShoppingList): Completable

    @Query("UPDATE shoppingLists SET archived =:archived WHERE shoppingListId =:shoppingListId")
    fun addToArchive(shoppingListId: Long, archived: Boolean = true): Completable
}