package dev.mysticmaster.tamngon247.feature.data.model

import dev.mysticmaster.tamngon247.feature.data.request.CategoryItemRequest

data class CategoryItem (
    val categoryName: String,
    val idImage: String,
    val status: Boolean,
    val id: String
){
    fun toCategoryItemRequest(): CategoryItemRequest{
        return CategoryItemRequest(
            categoryName = this.categoryName,
            idImage = this.idImage,
            status = this.status,
            id = this.id
        )
    }
}