package com.example.androidsp.data.local

import com.example.androidsp.data.local.db.model.HeroLocal

interface LocalDataSource {

    fun getHeros(): List<HeroLocal>

    fun insertHeros(heros: List<HeroLocal>)
}