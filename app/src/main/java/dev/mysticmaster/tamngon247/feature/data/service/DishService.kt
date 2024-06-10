package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.response.DishResponse
import dev.mysticmaster.tamngon247.feature.data.request.DishRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DishService {
    @GET("dish")
    suspend fun getAllDish(): Response<List<DishResponse>>

    @GET("dish/category/{id}")
    suspend fun getDishesByIdCategory(@Path("id") id: String): Response<List<DishResponse>>

    @GET("dish/id/{id}")
    suspend fun getDishById(@Path("id") id: String): Response<DishResponse?>

    @POST("dish")
    suspend fun addDish(@Body dishRequest: DishRequest): Response<Boolean>

    @PUT("dish/{id}")
    suspend fun updateDish(
        @Path("id") id: String,
        @Body dishRequest: DishRequest
    ): Response<Boolean>
}