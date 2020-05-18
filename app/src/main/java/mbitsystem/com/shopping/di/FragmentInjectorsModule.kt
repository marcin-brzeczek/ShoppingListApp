package mbitsystem.com.shopping.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mbitsystem.com.shopping.presentation.shoppingItem.ShoppingItemFragment
import mbitsystem.com.shopping.presentation.shoppingList.AddShoppingListFragment
import mbitsystem.com.shopping.presentation.archived.ArchivedListFragment
import mbitsystem.com.shopping.presentation.current.CurrentListFragment
import mbitsystem.com.shopping.presentation.details.DetailsListFragment

@Module
abstract class FragmentInjectorsModule {

    @ContributesAndroidInjector
    abstract fun provideCurrentListFragment(): CurrentListFragment

    @ContributesAndroidInjector
    abstract fun provideDetailsListFragment(): DetailsListFragment

    @ContributesAndroidInjector
    abstract fun provideArchivedListFragment(): ArchivedListFragment

    @ContributesAndroidInjector
    abstract fun provideAddShoppingList(): AddShoppingListFragment

    @ContributesAndroidInjector
    abstract fun provideAddShoppingItem(): ShoppingItemFragment
}
