package com.example.androidsp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.androidsp.data.local.db.model.HeroLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Query("SELECT * FROM heros")
    fun getAll(): Flow<List<HeroLocal>>

    @Insert
    fun insertAll(heros: List<HeroLocal>)

    @Query("UPDATE heros SET favorite = :fav WHERE id = :heroId")
    fun setFavorite(fav: Boolean, heroId: Int )

    @Update
    fun updateHero(hero: HeroLocal)

    @Query("SELECT favorite FROM heros WHERE id = :heroId")
    fun getFavorite(heroId: Int): Boolean

    @Query("SELECT COUNT(Id) FROM heros")
    fun getHerosCount(): Int
}