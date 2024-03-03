package com.example.androidsp.utils.fakes

import com.example.androidsp.data.local.LocalDataSource
import com.example.androidsp.data.local.db.model.HeroLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf

class FakeLocalDataSource: LocalDataSource{

    var dataBase = mutableListOf<HeroLocal>()
    override suspend fun getHeros(): Flow<List<HeroLocal>> {
        return flowOf(dataBase)
    }

    override fun getHerosCount(): Int {
        return dataBase.count()
    }

    override fun updateHero(hero: HeroLocal) {

    }

    override fun insertHeros(heros: List<HeroLocal>) {
        dataBase.addAll(heros)
    }

    override fun setFavorite(fav: Boolean, heroId: Int) {
        dataBase.first().favorite = fav
    }

    override fun getFavorite(heroId: Int): Boolean {
        return dataBase.first().favorite
    }
}