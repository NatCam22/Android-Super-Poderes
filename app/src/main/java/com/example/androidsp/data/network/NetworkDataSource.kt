package com.example.androidsp.data.network

import com.example.androidsp.data.network.model.HeroRemote

interface NetworkDataSource {
    suspend fun getHeros(): List<HeroRemote>
}