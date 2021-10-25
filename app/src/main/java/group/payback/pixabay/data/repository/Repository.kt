package group.payback.pixabay.data.repository

import group.payback.pixabay.network.model.BaseModel
import group.payback.pixabay.network.model.PhotoModel
import io.reactivex.Single

interface Repository {

    fun getPhotoRequest(param: BaseModel): Single<PhotoModel.BasePhoto>
}