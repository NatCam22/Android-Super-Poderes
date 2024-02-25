package com.example.androidsp.data.network

import com.example.androidsp.data.network.api.HeroApi
import com.example.androidsp.data.network.model.HeroRemote
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val api: HeroApi): NetworkDataSource {

    override suspend fun getHeros(): List<HeroRemote>{
        val heros = api.getHeros(API_KEY, HASH, TS)
        println(heros)
        return heros.data.results
    }

    companion object {
        const val API_KEY = "dffa33d6087a3474338accd6ba5258f3"
        const val HASH = "43838211bf22aa3bafa3a71458523e36"
        const val TS = 1
    }
}