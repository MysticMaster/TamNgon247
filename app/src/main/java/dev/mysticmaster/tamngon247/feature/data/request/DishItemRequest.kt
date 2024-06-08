package dev.mysticmaster.tamngon247.feature.data.request

import com.google.gson.annotations.SerializedName

data class DishItemRequest(
    val dishName: String,
    val idCategory: String,
    val idImage: String,
    val dishPrice: Int,
    val status: Boolean,
    @SerializedName("_id") var id: String? = null
)