package com.example.androidsp.data.network.model

import com.squareup.moshi.Json

data class HeroResponse<T>(
    @Json(name="data") val data: HeroResults<T>
)