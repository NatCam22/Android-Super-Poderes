package com.example.androidsp.data.network.model

import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Serie
import com.squareup.moshi.Json

data class ComicRemote(
    @Json(name="id")val id: Int,
    @Json(name="title")val title: String,
    @Json(name="thumbnail")val photo: HeroThumbnail
)

fun List<ComicRemote>.toUI(): List<Comic> = this.map{
    it.toUI()
}
fun ComicRemote.toUI(): Comic = with(this){
   Comic(this.id, this.title, "${photo.path}.${photo.extension}".replace("http", "https"))
}