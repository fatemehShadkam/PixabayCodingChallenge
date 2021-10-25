package group.payback.pixabay.data.request

import group.payback.pixabay.network.model.BaseModel
import group.payback.pixabay.data.execution.PostExecutionThread
import group.payback.pixabay.data.execution.RequestExecutor
import group.payback.pixabay.data.request.base.SingleRequest
import group.payback.pixabay.data.repository.Repository
import group.payback.pixabay.network.model.PhotoModel
import io.reactivex.Single
import javax.inject.Inject

class PhotoRequest @Inject
constructor(
    requestExecutor: RequestExecutor,
    postExecutionThread: PostExecutionThread,
    repository: Repository
) : SingleRequest<PhotoModel.BasePhoto, BaseModel>(requestExecutor, postExecutionThread, repository) {
    override fun interact(params: BaseModel): Single<PhotoModel.BasePhoto> {
        return repository.getPhotoRequest(params)
    }
}