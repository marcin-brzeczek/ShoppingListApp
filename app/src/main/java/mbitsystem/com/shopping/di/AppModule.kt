package mbitsystem.com.shopping.di

import dagger.Module
import dagger.Provides
import mbitsystem.com.shopping.utils.AppSchedulerProvider
import mbitsystem.com.shopping.utils.SchedulerProvider
import javax.inject.Singleton

@Module
 class AppModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}