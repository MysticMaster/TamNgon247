package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.response.DishResponse
import dev.mysticmaster.tamngon247.feature.data.request.DishRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface DishService {
    @GET("dish")
    suspend fun getAllDish(): Response<List<DishResponse>>

    @GET("dish/category/{id}")
    suspend fun getDishesByIdCategory(@Path("id") id: String): Response<List<DishResponse>>

    @GET("dish/id/{id}")
    suspend fun getDishById(@Path("id") id: String): Response<DishResponse?>

    @Multipart
    @POST("dish")
    suspend fun addDish(
        @Part file: MultipartBody.Part?,
        @PartMap parts: Map<String, @JvmSuppressWildcards RequestBody>
    ): Response<Boolean>

    @Multipart
    @PUT("dish/{id}")
    suspend fun updateDish(
        @Path("id") id: String,
        @Part file: MultipartBody.Part?,
        @PartMap parts: Map<String, @JvmSuppressWildcards RequestBody>
    ): Response<Boolean>

    @DELETE("dish/{id}")
    suspend fun deleteDish(
        @Path("id") id: String
    ): Response<Boolean>
}