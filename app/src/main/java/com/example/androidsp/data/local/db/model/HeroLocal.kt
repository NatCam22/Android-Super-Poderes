package com.example.androidsp.data.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidsp.domain.Hero

@Entity(tableName = "heros")
data class HeroLocal(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
)

fun HeroLocal.toUI(): Hero {
    return Hero(this.id, this.name, this.photo, this.description, this.favorite)
}
fun List<HeroLocal>.toUI(): List<Hero> = map{
    it.toUI()
}