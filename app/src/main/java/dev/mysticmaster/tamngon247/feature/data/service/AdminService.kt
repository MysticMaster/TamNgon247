package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.AdminModel
import dev.mysticmaster.tamngon247.feature.data.request.LoginRequest
import dev.mysticmaster.tamngon247.feature.data.response.AdminResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AdminService {
    @GET("admin/id/{_id}")
    suspend fun getAdminById(@Path("_id") id: String): Response<AdminResponse?>

    @POST("admin/login")
    suspend fun postAdminLogin(@Body loginRequest: LoginRequest): Response<AdminResponse?>

    @POST("admin")
    suspend fun registerAdmin(@Body adminModel: AdminModel): Response<Boolean>

    @PUT("admin/{_id}")
    suspend fun updateAdmin(
        @Path("_id") id: String,
        @Body adminModel: AdminModel
    ): Response<Boolean>

}