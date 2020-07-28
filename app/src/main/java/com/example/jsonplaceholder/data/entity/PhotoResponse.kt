package com.example.jsonplaceholder.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("total")
    @Expose
    var total: Long?,

    @SerializedName("totalHits")
    @Expose
    var totalHits: Long?,

    @SerializedName("hits")
    @Expose
    var hits: List<RemotePhoto>
)