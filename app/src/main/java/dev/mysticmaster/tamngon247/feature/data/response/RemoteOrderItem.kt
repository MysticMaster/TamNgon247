package dev.mysticmaster.tamngon247.feature.data.response

import com.google.gson.annotations.SerializedName

class RemoteOrderItem(
    @SerializedName("_id_customer")
    val  idCustomer : String,
    @SerializedName("phone")
    val  phone : String,
    @SerializedName("ward")
    val  ward : String,
    @SerializedName("street")
    val  street : String,
    @SerializedName("house_number")
    val  houseNumber : String,
    @SerializedName("city")
    val  city : String,
    @SerializedName("payment")
    val  payment : Boolean,
    @SerializedName("status")
    val  status : Byte,
    @SerializedName("_id")
    val  id : String
)