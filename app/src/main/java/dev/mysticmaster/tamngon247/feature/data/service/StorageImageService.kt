package mystic.master.tamngon247.feature.data.remote.service

import dev.mysticmaster.tamngon247.feature.data.model.StorageImageItem
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface StorageImageService {
    @Multipart
    @POST("storage-image/avatar")
    suspend fun uploadAvatar(@Part filePart: MultipartBody.Part): Response<StorageImageItem?>

    @Multipart
    @POST("storage-image/category")
    suspend fun uploadCategory(@Part filePart: MultipartBody.Part): Response<StorageImageItem?>

    @Multipart
    @POST("storage-image/dish")
    suspend fun uploadDish(@Part filePart: MultipartBody.Part): Response<StorageImageItem?>

    @DELETE("storage-image/avatar/{path}")
    suspend fun deleteAvatar(@Path("path") path: String): Response<Boolean>

    @DELETE("storage-image/category/{path}")
    suspend fun deleteCategory(@Path("path") path: String): Response<Boolean>

    @DELETE("storage-image/dish/{path}")
    suspend fun deleteDish(@Path("path") path: String): Response<Boolean>
}