package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName
import dev.mysticmaster.tamngon247.feature.data.model.CustomerModel

class CustomerResponse(
    @SerializedName("_id")
    val id: String,
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
    val imagePath: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("status")
    val status: Boolean,
) {
    fun toCustomerModel(): CustomerModel {
        return CustomerModel(
            id = this.id,
            fullName = this.fullName,
            username = this.username,
            password = this.password,
            email = this.email,
            phone = this.phone,
            ward = this.ward,
            street = this.street,
            houseNumber = this.houseNumber,
            city = this.city,
            imagePath = this.imagePath,
            imageUrl = this.imageUrl,
            status = this.status,
        )
    }
}