package dev.mysticmaster.tamngon247.feature.data.request

import com.google.gson.annotations.SerializedName

data class DishItemRequest(
    @SerializedName("dish_name") val dishName: String,
    @SerializedName("_id_category") val idCategory: String,
    @SerializedName("_id_image") val idImage: String?,
    @SerializedName("dish_price") val dishPrice: Int,
    @SerializedName("status") val status: Boolean,
    @SerializedName("_id") var id: String? = null
)