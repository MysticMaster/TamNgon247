package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.OrderItemItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface OrderItemService {
    @GET("order-item")
    suspend fun getAllOrderItem(): Response<List<OrderItemItem>>

    @GET("order-item/order/{_id}")
    suspend fun getOrderItemsByIdCategory(@Path("_id") id: String): Response<List<OrderItemItem>>

    @GET("order-item/id/{_id}")
    suspend fun getOrderItemById(@Path("_id") id: String): Response<OrderItemItem?>

    @POST("order-item")
    suspend fun addOrderItem(@Body orderItemItem: OrderItemItem): Response<Boolean>

    @PUT("order-item/{_id}")
    suspend fun updateOrderItem(
        @Path("_id") id: String,
        @Body orderItemItem: OrderItemItem
    ): Response<Boolean>
}