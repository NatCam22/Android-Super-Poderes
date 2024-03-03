package com.example.androidsp.data.local

import com.example.androidsp.data.local.db.model.HeroLocal
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getHeros(): Flow<List<HeroLocal>>

    fun getHerosCount(): Int

    fun updateHero(hero: HeroLocal)

    fun insertHeros(heros: List<HeroLocal>)

    fun setFavorite(fav: Boolean, heroId: Int)

    fun getFavorite(heroId: Int): Boolean

}