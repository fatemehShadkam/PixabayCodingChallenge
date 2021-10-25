package group.payback.pixabay.data.request.base

import group.payback.pixabay.data.execution.PostExecutionThread
import group.payback.pixabay.data.execution.RequestExecutor
import group.payback.pixabay.data.repository.Repository
import io.reactivex.Single

/**
 * @param Responses The response value emitted by the Observable.
 * @param Params The request value.
 */
abstract class SingleRequest<Responses, Params>(requestExecutor: RequestExecutor,
                                                postExecutionThread: PostExecutionThread,
                                                protected var repository: Repository) :
    Request<Single<Responses>, Params>(requestExecutor, postExecutionThread) {

    open fun execute(params: Params): Single<Responses> {
        return interact(params).applySchedulers()
    }

    protected abstract fun interact(params: Params): Single<Responses>

}