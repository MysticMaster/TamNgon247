package dev.mysticmaster.tamngon247.feature.data.request

import com.google.gson.annotations.SerializedName

data class CategoryItemRequest(
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("_id_image") val idImage: String,
    @SerializedName("status") val status: Boolean,
    @SerializedName("_id") var id: String? = null
)