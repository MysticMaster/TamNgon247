package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName

class RemoteCustomerItem(
    @SerializedName("full_name")
    val fullName:String,
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
    val houseNumber : String,
    @SerializedName("city")
    val city: String,
    @SerializedName("_id_image")
    val idImage: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("_id")
    val id: String
)