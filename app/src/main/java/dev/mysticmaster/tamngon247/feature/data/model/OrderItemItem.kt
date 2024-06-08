package dev.mysticmaster.tamngon247.feature.data.model

data class OrderItemItem(
    val idOrder: String,
    val idDish: String,
    val price: Int,
    val quantity: Int,
    val status: Boolean,
    val id: String
)