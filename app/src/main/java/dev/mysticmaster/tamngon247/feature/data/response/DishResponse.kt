package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName
import dev.mysticmaster.tamngon247.feature.data.model.DishModel

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
    val status: Boolean
) {
    fun toDishModel(): DishModel {
        return DishModel(
            id = this.id,
            dishName = this.dishName,
            idCategory = this.idCategory,
            imagePath = this.imagePath,
            imageUrl = this.imageUrl,
            dishPrice = this.dishPrice,
            status = this.status
        )
    }
}