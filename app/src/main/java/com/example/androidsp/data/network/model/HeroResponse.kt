package com.example.androidsp.data.network.model

import com.squareup.moshi.Json

data class HeroResponse (
    @Json(name="data") val data: HeroResults
)