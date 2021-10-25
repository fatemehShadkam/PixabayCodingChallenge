package group.payback.pixabay.data.execution

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * provides background thread to prevent locking ui when calling network api
 */

class NetworkJobExecutor : RequestExecutor {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}