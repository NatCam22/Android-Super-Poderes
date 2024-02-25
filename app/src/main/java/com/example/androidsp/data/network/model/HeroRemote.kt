package com.example.androidsp.data.network.model

import com.example.androidsp.data.local.db.model.HeroLocal
import com.squareup.moshi.Json

data class HeroRemote (
    @Json(name="id")val id: String,
    @Json(name="name")val name: String,
    @Json(name="description")val description: String,
    @Json(name="thumbnail")val photo: HeroThumbnail
)

fun List<HeroRemote>.toLocal(): List<HeroLocal> = this.map{
    it.toLocal()
}

fun HeroRemote.toLocal(): HeroLocal = with(this){
    HeroLocal(id, name, description ,"${photo.path}${photo.extension}", false)
}