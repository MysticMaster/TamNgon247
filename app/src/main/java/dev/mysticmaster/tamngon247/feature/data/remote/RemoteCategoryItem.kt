package dev.mysticmaster.tamngon247.feature.data.remote

import com.google.gson.annotations.SerializedName
import dev.mysticmaster.tamngon247.feature.data.model.CategoryItem

class RemoteCategoryItem(
    @SerializedName("category_name")
    var categoryName: String,
    @SerializedName("_id_image")
    var idImage: String,
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("_id")
    var id: String
) {
    fun toCategoryItem(): CategoryItem {
        return CategoryItem(
            categoryName = categoryName,
            idImage = idImage,
            status = status,
            id = id
        )
    }
}