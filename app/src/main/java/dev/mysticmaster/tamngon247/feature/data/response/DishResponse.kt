package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName

class DishResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("dish_name")
    val dishName: String,
    @SerializedName("_id_category")
    val idCategory: String,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("dish_price")
    val dishPrice: Int,
    @SerializedName("status")
    val status: Boolean,
)