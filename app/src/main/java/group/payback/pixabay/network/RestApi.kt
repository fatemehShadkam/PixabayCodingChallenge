package group.payback.pixabay.network


import group.payback.pixabay.network.model.PhotoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    companion object {
        const val BASE_URL = "https://pixabay.com/"
        const val API_KEY = "23963238-9dc076f37970b61940f89350a"
    }

    @GET("api/?")
    fun getPhotos(@Query("q") query:String? = "",
                  @Query("per_page") pageSize:Int? = 20,
                  @Query("page") pageNum :Int? ): Single<PhotoModel.BasePhoto>

}