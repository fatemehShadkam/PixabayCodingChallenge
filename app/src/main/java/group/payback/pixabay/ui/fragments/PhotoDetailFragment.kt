package group.payback.pixabay.ui.fragments

import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import group.payback.pixabay.R
import group.payback.pixabay.ui.base.BaseFragment
import group.payback.pixabay.ui.viewmodel.PhotoDetailViewModel
import kotlinx.android.synthetic.main.fragment_photo_details.*


class PhotoDetailFragment : BaseFragment<Boolean, PhotoDetailViewModel>() {

    override fun handleState(state: Boolean) {}

    override fun getLayout(): Int = R.layout.fragment_photo_details


    override fun onCreateCompleted() {
        setHasOptionsMenu(true)
        createViewModel(PhotoDetailViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        bindBundle()
    }

    private fun bindBundle() {
        val radius = context?.resources?.getDimensionPixelSize(R.dimen.corner_radius)
        arguments?.apply {
            PhotoDetailFragmentArgs.fromBundle(this).apply {
                val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
                imageview_details_largePhoto.setImageURI(imgUri)
                context?.let {
                    Glide.with(it)
                        .load(imgUri)
                        .transform(radius?.let { it1 -> RoundedCorners(it1) })
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.animation_loading)
                                .error(R.drawable.ic_default_image)
                        )
                        .into(imageview_details_largePhoto)
                }

                textview_details_tags.text = tags
                textview_details_username.text = userName
                textView_details_likes.text = likes
                textView_details_downloads.text = downloads
                textView_details_comments.text = comments

            }
        }
    }

}