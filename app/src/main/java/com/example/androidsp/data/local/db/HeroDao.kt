package com.example.androidsp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidsp.data.local.db.model.HeroLocal

@Dao
interface HeroDao {
    @Query("SELECT * FROM heros")
    fun getAll(): List<HeroLocal>

    @Insert
    fun insertAll(heros: List<HeroLocal>)
}