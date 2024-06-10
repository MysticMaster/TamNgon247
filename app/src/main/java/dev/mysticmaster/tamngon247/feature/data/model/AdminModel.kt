package dev.mysticmaster.tamngon247.feature.data.model

data class AdminModel(
    val id: String,
    val fullName: String,
    val username: String,
    val password: String,
    val imagePath: String?,
    val imageUrl: String?,
    val status: Boolean
)
