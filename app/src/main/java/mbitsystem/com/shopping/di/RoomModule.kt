package mbitsystem.com.shopping.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import mbitsystem.com.shopping.data.dao.ShoppingItemDao
import mbitsystem.com.shopping.data.dao.ShoppingListDao
import mbitsystem.com.shopping.data.db.AppDatabase
import javax.inject.Singleton

private const val DB_NAME = "ShoppingListDatabase"

@Module
class RoomModule {

    @Singleton
    @Provides
    fun providesAppDatabase(application: Application): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
        .build()

    @Singleton
    @Provides
    fun providesShoppingListDao(appDatabase: AppDatabase): ShoppingListDao = appDatabase.shoppingListDao()

    @Singleton
    @Provides
    fun providesShoppingItemDao(appDatabase: AppDatabase): ShoppingItemDao = appDatabase.shoppingItemDao()
}