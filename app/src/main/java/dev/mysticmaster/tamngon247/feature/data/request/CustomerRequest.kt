package dev.mysticmaster.tamngon247.feature.data.request

import com.google.gson.annotations.SerializedName

data class CustomerRequest(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("ward")
    val ward: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("house_number")
    val houseNumber: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("image_path")
    val imagePath: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("status")
    val status: Boolean,
)
