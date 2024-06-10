package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName
import dev.mysticmaster.tamngon247.feature.data.model.AdminModel

data class AdminResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("status")
    val status: Boolean,
) {
    fun toAdminModel(): AdminModel {
        return AdminModel(
            id = this.id,
            fullName = this.fullName,
            username = this.username,
            password = this.password,
            imagePath = this.imagePath,
            imageUrl = this.imageUrl,
            status = this.status
        )
    }
}
