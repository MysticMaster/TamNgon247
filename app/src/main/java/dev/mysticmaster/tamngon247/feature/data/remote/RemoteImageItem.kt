package dev.mysticmaster.tamngon247.feature.data.remote

import com.google.gson.annotations.SerializedName

class RemoteImageItem(
    @SerializedName("path")
    val path: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("_id")
    val id: String
)