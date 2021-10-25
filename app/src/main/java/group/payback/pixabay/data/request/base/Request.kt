package group.payback.pixabay.data.request.base

import group.payback.pixabay.data.execution.PostExecutionThread
import group.payback.pixabay.data.execution.RequestExecutor
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * Each [Request] of the system orchestrate the flow of data to and from the entities.
 * Outer layers of system can execute use cases by calling [execute]} method.
 * ALso you can use [requestExecutor] to execute the job in a background thread and
 * [postExecutionThread] to post the result to another thread(UI thread).
 * @param Responses response type for use case.
 * @param Params input parameters for use case
 */
abstract class Request<Responses, Params>(
    protected val requestExecutor: RequestExecutor,
    protected val postExecutionThread: PostExecutionThread
) {
    private fun getRequestExecutor(): Scheduler {
        return requestExecutor.scheduler
    }

    private fun getPostExecutionThread(): Scheduler {
        return postExecutionThread.scheduler
    }

    fun <T> Single<T>.applySchedulers(): Single<T> {
        return subscribeOn(getRequestExecutor()).observeOn(getPostExecutionThread())
    }
}