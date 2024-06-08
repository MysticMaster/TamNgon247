package dev.mysticmaster.tamngon247.feature.data.model

data class CustomerItem(
    val fullName: String,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
    val ward: String,
    val street: String,
    val houseNumber: String,
    val city: String,
    val idImage: String,
    val status: Boolean,
    val id: String
)