package com.example.androidsp.data.local

import com.example.androidsp.data.local.db.HeroDao
import com.example.androidsp.data.local.db.model.HeroLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDao): LocalDataSource {
    override suspend fun getHeros(): Flow<List<HeroLocal>> {
        return dao.getAll()
    }
    override fun getHerosCount(): Int {
        return dao.getHerosCount()
    }

    override fun updateHero(hero: HeroLocal) {
        dao.updateHero(hero)
    }

    override  fun insertHeros(heros: List<HeroLocal>) {
        return dao.insertAll(heros)
    }

    override fun setFavorite(fav: Boolean, heroId: Int) {
        dao.setFavorite(fav, heroId)
    }
    override fun getFavorite(heroId: Int): Boolean {
        return dao.getFavorite(heroId)
    }
}