package dev.mysticmaster.tamngon247.feature.data.remote

import com.google.gson.annotations.SerializedName

class RemoteStorageImageItem (
    @SerializedName("type")
    val type :String,
    @SerializedName("path")
    val path : String,
    @SerializedName("downloadURL")
    val downloadURL: String
)