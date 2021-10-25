package group.payback.pixabay.data.dataSource

import androidx.paging.PositionalDataSource
import group.payback.pixabay.data.request.PhotoRequest
import group.payback.pixabay.network.model.PhotoModel
import javax.inject.Inject
import group.payback.pixabay.network.model.BaseModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


class PhotoPositionalDataSource @Inject constructor(
    private val getPhotoRequest: PhotoRequest
) : PositionalDataSource<PhotoModel.PhotoResponse>(), Disposable {

    private var disposing = false
    override fun isDisposed(): Boolean {
        return disposing
    }

    override fun dispose() {
        disposing = true
        compositeDisposable.clear()
    }

    private var filter: String = ""
    fun setFilter(filter: String) {
        this.filter = filter
    }

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun computeCount(): Int {
        // actual count code here
        return getPhotoRequest.execute(BaseModel()).blockingGet().total
    }


    private fun loadRangeInternal(startPosition: Int, loadCount: Int) {
        // actual load code here

        val pageNum = startPosition / loadCount + 1
        val disposable = getPhotoRequest.execute(BaseModel(filter, loadCount, pageNum)).subscribe({ response ->
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PhotoModel.PhotoResponse>) {

        val pageNum = params.startPosition / params.loadSize + 1
        val disposable = getPhotoRequest.execute(BaseModel(filter, params.loadSize, pageNum)).subscribe({ response ->
            callback.onResult(response.response);
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PhotoModel.PhotoResponse>) {
        val totalCount = 2000
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)

        val pageNum = position / loadSize + 1
        val disposable = getPhotoRequest.execute(BaseModel(filter, loadSize, pageNum)).subscribe({ response ->
            callback.onResult(response.response, position, response.total)
        }, { t: Throwable? ->
            Timber.e(t)
        })
        compositeDisposable.add(disposable)

    }
}