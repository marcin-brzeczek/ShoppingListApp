package mbitsystem.com.shopping

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import mbitsystem.com.shopping.utils.SchedulerProvider

class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun io() : Scheduler = testScheduler
    override fun computation(): Scheduler  = testScheduler
}