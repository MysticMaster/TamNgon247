package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.ImageItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ImageService {
    @GET("image/{_id}")
    suspend fun getImageById(@Path("_id") id: String): Response<ImageItem?>

    @POST("image")
    suspend fun addImage(@Body imageItem: ImageItem): Response<ImageItem?>

    @PUT("image/{_id}")
    suspend fun updateImage(
        @Path("_id") id: String,
        @Body imageItem: ImageItem): Response<Boolean>
}