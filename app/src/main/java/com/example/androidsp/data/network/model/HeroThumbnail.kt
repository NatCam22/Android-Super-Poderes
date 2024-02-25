package com.example.androidsp.data.network.model

import com.squareup.moshi.Json

data class HeroThumbnail (
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)