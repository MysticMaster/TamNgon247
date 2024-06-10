package dev.mysticmaster.tamngon247.feature.data.model

data class DishModel(
    val id: String,
    val dishName: String,
    val idCategory: String,
    val imagePath: String?,
    val imageUrl: String?,
    val dishPrice: Int,
    val status: Boolean,
)