package group.payback.pixabay.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import group.payback.pixabay.R
import group.payback.pixabay.network.model.PhotoModel
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bind(photo: PhotoModel.PhotoResponse?) {

            photo?.apply {
                val imgUri = previewImageUrl.toUri().buildUpon().scheme("https").build()
                itemView.apply {
                    Glide.with(imageview_item_preview.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.animation_loading)
                                .error(R.drawable.ic_default_image)
                        )
                        .into(imageview_item_preview)
                    textView_item_username.text = userName
                    textView_item_tags.text = tags
                }
            }
    }

    companion object {
        fun from(parent: ViewGroup): PhotoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater
                .inflate(R.layout.item_photo, parent, false)

            return PhotoViewHolder(view)
        }
    }

}