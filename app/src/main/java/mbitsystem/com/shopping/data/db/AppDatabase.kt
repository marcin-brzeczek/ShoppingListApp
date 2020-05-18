package mbitsystem.com.shopping.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mbitsystem.com.shopping.data.RoomConverters
import mbitsystem.com.shopping.data.dao.ShoppingItemDao
import mbitsystem.com.shopping.data.dao.ShoppingListDao
import mbitsystem.com.shopping.data.model.ShoppingItem
import mbitsystem.com.shopping.data.model.ShoppingList

@TypeConverters(RoomConverters::class)
@Database(entities = [ShoppingList::class, ShoppingItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao

    abstract fun shoppingItemDao(): ShoppingItemDao
}
