package group.payback.pixabay.network.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName

sealed class PhotoModel {

    data class BasePhoto(@SerializedName("totalHits") val totalHits: Int,
                         @SerializedName("total") val total: Int,
                         @SerializedName("hits") val response: List<PhotoResponse>
                      )

    data class PhotoResponse(@SerializedName("id") val id: String ="",
                             @Bindable @SerializedName("user") val userName: String = "",
                             @Bindable  @SerializedName("largeImageURL") val largeImageUrl: String = "",
                             @SerializedName("webformatURL") val webFormatUrl: String= "",
                             @Bindable  @SerializedName("previewURL") val previewImageUrl: String= "",
                             @SerializedName("userImageURL") val userImageUrl: String= "",
                             @Bindable @SerializedName("comments") val commentNumber: String= "",
                             @Bindable @SerializedName("likes") val likeNumber: String= "",
                             @Bindable @SerializedName("tags") val tags: String= "",
                             @Bindable @SerializedName("downloads") val downloadNumber: String= "") :
        BaseObservable()
}