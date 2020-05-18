package mbitsystem.com.shopping.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import mbitsystem.com.shopping.presentation.shoppingItem.ShoppingItemViewModel
import mbitsystem.com.shopping.presentation.shoppingList.AddShoppingListViewModel
import mbitsystem.com.shopping.presentation.archived.ArchivedListViewModel
import mbitsystem.com.shopping.presentation.current.CurrentListViewModel
import mbitsystem.com.shopping.presentation.details.DetailsListViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentListViewModel::class)
    abstract fun bindSplashViewModel(viewModel: CurrentListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddShoppingListViewModel::class)
    abstract fun bindAddShoppingListViewModel(viewModel: AddShoppingListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingItemViewModel::class)
    abstract fun bindAAddShoppingItemViewModel(viewModel: ShoppingItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsListViewModel::class)
    abstract fun bindDetailsListViewModel(viewModel: DetailsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArchivedListViewModel::class)
    abstract fun bindArchivedListViewModel(viewModel: ArchivedListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: InjectingViewModelFactory): ViewModelProvider.Factory
}