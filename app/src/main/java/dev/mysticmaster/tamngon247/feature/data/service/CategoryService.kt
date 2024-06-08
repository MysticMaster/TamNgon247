package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.CategoryItem
import dev.mysticmaster.tamngon247.feature.data.remote.RemoteCategoryItem
import dev.mysticmaster.tamngon247.feature.data.request.CategoryItemRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoryService {
    @GET("category")
    suspend fun getAllCategory(): Response<List<RemoteCategoryItem>>

    @GET("category/id/{id}")
    suspend fun getCategoryById(@Path("id") id: String): Response<RemoteCategoryItem?>

    @POST("category")
    suspend fun addCategory(@Body categoryItemRequest: CategoryItemRequest): Response<Boolean>

    @PUT("category/{id}")
    suspend fun updateCategory(
        @Path("id") id: String,
        @Body categoryItemRequest: CategoryItemRequest
    ): Response<Boolean>

    @DELETE("category/{id}")
    suspend fun deleteCategory(
        @Path("id") id: String
    ): Response<Boolean>
}