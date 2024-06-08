package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.OrderItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderService {
    @GET("order")
    suspend fun getAllOrder(): Response<List<OrderItem>>

    @GET("order/customer/{_id}")
    suspend fun getOrdersByIdCategory(@Path("_id") id: String): Response<List<OrderItem>>

    @GET("order/id/{_id}")
    suspend fun getOrderById(@Path("_id") id: String): Response<OrderItem?>

    @POST("order")
    suspend fun addOrder(@Body orderItem: OrderItem): Response<Boolean>

    @PUT("order/{_id}")
    suspend fun updateOrder(
        @Path("_id") id: String,
        @Body orderItem: OrderItem
    ): Response<Boolean>
}