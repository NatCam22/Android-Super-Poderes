package com.example.androidsp.data

import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.Serie
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getHeroList(): Flow<List<Hero>>

    suspend fun getHero(id: Int): Hero
    suspend fun getSeries(id: Int): List<Serie>
    suspend fun getComics(id: Int): List<Comic>
    suspend fun likeHero(hero: Hero): Boolean
}