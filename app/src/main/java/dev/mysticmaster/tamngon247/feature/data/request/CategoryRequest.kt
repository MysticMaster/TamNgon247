package dev.mysticmaster.tamngon247.feature.data.request

import com.google.gson.annotations.SerializedName

data class CategoryRequest(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("image_path")
    var imagePath: String? = null,
    @SerializedName("image_url")
    var imageUrl: String? = null,
    @SerializedName("status")
    val status: Boolean,
)