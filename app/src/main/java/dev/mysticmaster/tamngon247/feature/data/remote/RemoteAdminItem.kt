package dev.mysticmaster.tamngon247.feature.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteAdminItem(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("_id_image")
    val idImage: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("_id")
    val id: String
)
