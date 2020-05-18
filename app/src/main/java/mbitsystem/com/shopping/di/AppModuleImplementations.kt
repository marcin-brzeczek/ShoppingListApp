package mbitsystem.com.shopping.di

import dagger.Binds
import dagger.Module
import mbitsystem.com.shopping.utils.SchedulerProviderDelegate
import mbitsystem.com.shopping.utils.SchedulerProviderDelegateImpl

@Module
abstract class AppModuleImplementations {

    @Binds
    abstract fun bindsSchedulerProviderDelegate(
        schedulerProviderDelegateImpl: SchedulerProviderDelegateImpl
    ): SchedulerProviderDelegate
}