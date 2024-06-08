package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.AdminItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AdminService {
    @GET("admin/id/{_id}")
    suspend fun getAdminById(@Path("_id") id: String): Response<AdminItem?>

    @POST("admin/login")
    suspend fun postAdminLogin(@Body username: String, password: String): Response<AdminItem?>

    @POST("admin")
    suspend fun registerAdmin(@Body adminItem: AdminItem): Response<Boolean>

    @PUT("admin/{_id}")
    suspend fun updateAdmin(
        @Path("_id") id: String,
        @Body adminItem: AdminItem
    ): Response<Boolean>

}