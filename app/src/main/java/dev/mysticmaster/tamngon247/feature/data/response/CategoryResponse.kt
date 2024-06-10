package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName
import dev.mysticmaster.tamngon247.feature.data.model.CategoryModel

class CategoryResponse(
    @SerializedName("_id")
    val id: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("image_path")
    val imagePath: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("status")
    val status: Boolean,
) {
    fun toCategoryModel(): CategoryModel {
        return CategoryModel(
            id = this.id,
            categoryName = this.categoryName,
            imagePath = this.imagePath,
            imageUrl = this.imageUrl,
            status = this.status
        )
    }
}