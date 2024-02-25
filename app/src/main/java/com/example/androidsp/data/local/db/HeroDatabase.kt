package com.example.androidsp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidsp.data.local.db.model.HeroLocal

@Database(entities = [HeroLocal::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao
}