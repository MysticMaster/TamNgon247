package dev.mysticmaster.tamngon247.feature.data.request

import com.google.gson.annotations.SerializedName

data class DishRequest(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("dish_name")
    val dishName: String,
    @SerializedName("_id_category")
    val idCategory: String,
    @SerializedName("image_path")
    var imagePath: String? = null,
    @SerializedName("image_url")
    var imageUrl: String? = null,
    @SerializedName("dish_price")
    val dishPrice: Int,
    @SerializedName("status")
    val status: Boolean,
)