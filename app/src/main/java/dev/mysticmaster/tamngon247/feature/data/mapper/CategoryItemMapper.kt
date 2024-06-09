package dev.mysticmaster.tamngon247.feature.data.mapper

data class CategoryItemMapper(
    var categoryName: String,
    var idImage: String? = null,
    var path: String? = null,
    var url: String? =null,
    var status: Boolean,
    var id: String
)