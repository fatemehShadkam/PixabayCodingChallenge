package group.payback.pixabay.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import group.payback.pixabay.network.model.PhotoModel
import timber.log.Timber

typealias ClickListener = (PhotoModel.PhotoResponse) -> Unit

class PhotoAdapter(private val clickListener: ClickListener) :
    PagedListAdapter<PhotoModel.PhotoResponse, PhotoViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)

        with(holder) {
            bind(photo)
            photo?.let {
                itemView.setOnClickListener {
                    clickListener(photo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder.from(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<PhotoModel.PhotoResponse>() {
            override fun areItemsTheSame(oldItem: PhotoModel.PhotoResponse, newItem: PhotoModel.PhotoResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoModel.PhotoResponse, newItem: PhotoModel.PhotoResponse): Boolean =
                oldItem == newItem
        }
    }
}