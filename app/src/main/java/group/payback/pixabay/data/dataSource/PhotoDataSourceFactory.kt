package group.payback.pixabay.data.dataSource

import androidx.paging.DataSource
import group.payback.pixabay.network.model.PhotoModel
import javax.inject.Inject

class PhotoDataSourceFactory @Inject constructor(
    private val dataSource: PhotoPositionalDataSource
) : DataSource.Factory<Int, PhotoModel.PhotoResponse>() {


    fun setFilter(filter: String) {
        dataSource.setFilter(filter)
    }

    override fun create(): DataSource<Int, PhotoModel.PhotoResponse> {
            return dataSource
    }

}