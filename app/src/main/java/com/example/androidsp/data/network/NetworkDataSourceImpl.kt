package com.example.androidsp.data.network

import com.example.androidsp.data.network.api.HeroApi
import com.example.androidsp.data.network.model.ComicRemote
import com.example.androidsp.data.network.model.HeroRemote
import com.example.androidsp.data.network.model.SerieRemote
import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Serie
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val api: HeroApi): NetworkDataSource {

    override suspend fun getHeros(): List<HeroRemote>{
        val heros = api.getHeros(API_KEY, HASH, TS)
        return heros.data.results
    }

    override suspend fun getHero(id: Int): HeroRemote {
        val hero = api.getHeroDetail(id, API_KEY, HASH, TS)
        return hero.data.results[0]
    }

    override suspend fun getSeries(id: Int): List<SerieRemote> {
        val series = api.getHeroSeries(id,API_KEY, HASH, TS)
        return series.data.results
    }

    override suspend fun getComics(id: Int): List<ComicRemote> {
        val series = api.getHeroComics(id, API_KEY, HASH, TS)
        return series.data.results
    }

    companion object {
        const val API_KEY = "e9a482d4a0d84cf2c3bc2da259a5ca25"
        const val HASH = "f1e0760e353d6455aad5229930c701b3"
        const val TS = 1
    }
}