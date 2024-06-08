package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.DishItem
import dev.mysticmaster.tamngon247.feature.data.remote.RemoteDishItem
import dev.mysticmaster.tamngon247.feature.data.request.DishItemRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DishService {
    @GET("dish")
    suspend fun getAllDish(): Response<List<RemoteDishItem>>

    @GET("dish/category/{id}")
    suspend fun getDishesByIdCategory(@Path("id") id: String): Response<List<RemoteDishItem>>

    @GET("dish/id/{id}")
    suspend fun getDishById(@Path("id") id: String): Response<RemoteDishItem?>

    @POST("dish")
    suspend fun addDish(@Body dishItemRequest: DishItemRequest): Response<Boolean>

    @PUT("dish/{id}")
    suspend fun updateDish(
        @Path("id") id: String,
        @Body dishItemRequest: DishItemRequest
    ): Response<Boolean>
}