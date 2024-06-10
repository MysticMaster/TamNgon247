package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.response.CategoryResponse
import dev.mysticmaster.tamngon247.feature.data.request.CategoryRequest
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

interface CategoryService {
    @GET("category")
    suspend fun getAllCategory(): Response<List<CategoryResponse>>

    @GET("category/id/{id}")
    suspend fun getCategoryById(@Path("id") id: String): Response<CategoryResponse?>

    @Multipart
    @POST("category")
    suspend fun addCategory(
        @Part file: MultipartBody.Part?,
        @PartMap parts: Map<String, @JvmSuppressWildcards RequestBody>
    ): Response<Boolean>

    @Multipart
    @PUT("category/{id}")
    suspend fun updateCategory(
        @Path("id") id: String,
        @Part file: MultipartBody.Part?,
        @PartMap parts: Map<String, @JvmSuppressWildcards RequestBody>
    ): Response<Boolean>

    @DELETE("category/{id}")
    suspend fun deleteCategory(
        @Path("id") id: String
    ): Response<Boolean>
}