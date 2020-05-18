package mbitsystem.com.shopping.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import mbitsystem.com.shopping.InitApp
import pl.nextapps.spark.wallet.di.module.schedulers.SchedulersModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModuleImplementations::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityInjectorsModule::class,
        FragmentInjectorsModule::class,
        ViewModelModule::class,
        SchedulersModule::class,
        RoomModule::class]
)
interface AppComponent : AndroidInjector<InitApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}