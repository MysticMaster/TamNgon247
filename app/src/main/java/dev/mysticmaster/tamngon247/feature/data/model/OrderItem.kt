package dev.mysticmaster.tamngon247.feature.data.model

data class OrderItem(
    val  idCustomer : String,
    val  phone : String,
    val  ward : String,
    val  street : String,
    val  houseNumber : String,
    val  city : String,
    val  payment : Boolean,
    val  status : Byte,
    val  id : String
)