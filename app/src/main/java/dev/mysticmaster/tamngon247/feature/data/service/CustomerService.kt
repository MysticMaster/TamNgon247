package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.CustomerModel
import dev.mysticmaster.tamngon247.feature.data.request.LoginRequest
import dev.mysticmaster.tamngon247.feature.data.response.CustomerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerService {
    @GET("customer/id/{_id}")
    suspend fun getCustomerById(@Path("_id") id: String): Response<CustomerResponse?>

    @POST("customer/login")
    suspend fun postCustomerLogin(@Body loginRequest: LoginRequest): Response<CustomerResponse?>

    @POST("customer")
    suspend fun registerCustomer(@Body customerModel: CustomerModel): Response<Boolean>

    @PUT("customer/{_id}")
    suspend fun updateCustomer(
        @Path("_id") _id: String,
        @Body customerModel: CustomerModel
    ): Response<Boolean>

}