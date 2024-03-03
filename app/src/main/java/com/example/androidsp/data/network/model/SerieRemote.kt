package com.example.androidsp.data.network.model

import com.example.androidsp.data.local.db.model.HeroLocal
import com.example.androidsp.domain.Serie
import com.squareup.moshi.Json

data class SerieRemote(
    @Json(name="id")val id: Int,
    @Json(name="title")val title: String,
    @Json(name="thumbnail")val photo: HeroThumbnail
)

fun List<SerieRemote>.toUI(): List<Serie> = this.map{
    it.toUI()
}
fun SerieRemote.toUI(): Serie = with(this){
    Serie(this.id, this.title, "${photo.path}.${photo.extension}".replace("http", "https"))
}
