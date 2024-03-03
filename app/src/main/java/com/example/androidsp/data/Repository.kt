package com.example.androidsp.data

import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.Serie

interface Repository {
    suspend fun getHeroList(): List<Hero>

    suspend fun getHero(id: Int): Hero
    suspend fun getSeries(id: Int): List<Serie>
    suspend fun getComics(id: Int): List<Comic>
}