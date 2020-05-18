package pl.nextapps.spark.wallet.di.module.schedulers

import javax.inject.Qualifier

@Qualifier
annotation class RunOn(val value: SchedulerContext = SchedulerContext.IO)
