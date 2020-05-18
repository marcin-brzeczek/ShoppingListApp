package pl.nextapps.spark.wallet.di.module.schedulers

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mbitsystem.com.shopping.utils.SchedulersProvider
import javax.inject.Singleton

@Module
object SchedulersModule {

    @Singleton
    @JvmStatic
    @Provides
    fun provideSchedulersProvider(): SchedulersProvider = object : SchedulersProvider {
        @Provides
        @RunOn(SchedulerContext.IO)
        override fun io() = Schedulers.io()

        @Provides
        @RunOn(SchedulerContext.COMPUTATION)
        override fun computation() = Schedulers.computation()

        @Provides
        @RunOn(SchedulerContext.UI)
        override fun ui() = AndroidSchedulers.mainThread()
    }
}