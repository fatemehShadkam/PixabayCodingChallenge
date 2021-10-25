package group.payback.pixabay.ui.viewmodel

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import group.payback.pixabay.data.dataSource.PhotoDataSourceFactory
import group.payback.pixabay.network.model.PhotoModel
import group.payback.pixabay.ui.base.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE_HINT = 40

class PhotosViewModel @Inject constructor(
    private val dataSourceFactory: PhotoDataSourceFactory
) : BaseViewModel<PagedList<PhotoModel.PhotoResponse>>() {

    var cachedFilter: String = ""

    fun setFilter(filter: String) {
        dataSourceFactory.setFilter(if (cachedFilter.isEmpty()) filter else cachedFilter)
    }

    init {
        createLiveData()
    }

    fun createLiveData() {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        this.stateLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build();
    }


}