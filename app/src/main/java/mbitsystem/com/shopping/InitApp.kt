package mbitsystem.com.shopping

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import mbitsystem.com.shopping.di.DaggerAppComponent
import timber.log.Timber

class InitApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
