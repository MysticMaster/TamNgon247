package dev.mysticmaster.tamngon247.feature.data.mapper

data class DishItemMapper(
    var dishName: String,
    var categoryName: String,
    var idImage: String? = null,
    var path: String? = null,
    var url: String? =null,
    var dishPrice: Int,
    var status: Boolean,
    var id: String
)
