package com.example.androidsp.data.network

import com.example.androidsp.data.network.model.HeroRemote
import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Serie

interface NetworkDataSource {
    suspend fun getHeros(): List<HeroRemote>
    suspend fun getHero(id: Int): HeroRemote

    suspend fun getSeries(id: Int): List<Serie>
    suspend fun getComics(id: Int): List<Comic>

}