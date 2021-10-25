package group.payback.pixabay.data.execution

import io.reactivex.Scheduler

/**
 * Represents an asynchronous execution for [request.Observablerequest].
 * It's useful to execute use cases out of
 * the UI thread to prevent it from freezing.
 */
interface RequestExecutor {
    val scheduler: Scheduler
}