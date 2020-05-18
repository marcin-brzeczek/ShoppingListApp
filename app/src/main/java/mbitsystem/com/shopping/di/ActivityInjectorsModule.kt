package mbitsystem.com.shopping.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mbitsystem.com.shopping.presentation.main.MainActivity

@Module
abstract class ActivityInjectorsModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivityInjector(): MainActivity
}