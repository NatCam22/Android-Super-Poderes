package com.example.androidsp.data.network.model

import com.squareup.moshi.Json

data class HeroResults <T>(
    @Json(name = "results") val results: List<T>
)