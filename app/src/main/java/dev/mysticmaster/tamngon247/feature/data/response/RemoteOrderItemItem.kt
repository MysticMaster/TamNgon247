package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName

class RemoteOrderItemItem(
    @SerializedName("_id_order")
    val idOrder: String,
    @SerializedName("_id_dish")
    val idDish: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("_id")
    val id: String
)