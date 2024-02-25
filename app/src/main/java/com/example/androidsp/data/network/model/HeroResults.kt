package com.example.androidsp.data.network.model

import com.squareup.moshi.Json

data class HeroResults(
    @Json(name = "results") val results: List<HeroRemote>
)