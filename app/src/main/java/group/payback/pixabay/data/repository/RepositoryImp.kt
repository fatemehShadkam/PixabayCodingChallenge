package group.payback.pixabay.data.repository

import group.payback.pixabay.network.model.BaseModel
import group.payback.pixabay.network.RestApi
import group.payback.pixabay.network.model.PhotoModel
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class RepositoryImp @Inject constructor(private val restApi: RestApi) : Repository {
    override fun getPhotoRequest(param: BaseModel): Single<PhotoModel.BasePhoto> {
        return Single.create { emitter ->
            restApi.getPhotos(param.query, param.pageSize, param.pageNum).subscribe({ response ->
                Timber.d("response : %s", response)
                emitter.onSuccess(response)
            }, {
                emitter.onError(it)
            })
        }
    }


}