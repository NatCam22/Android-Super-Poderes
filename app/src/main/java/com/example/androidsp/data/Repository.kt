package com.example.androidsp.data

import com.example.androidsp.domain.Hero

interface Repository {
    suspend fun getHeroList(): List<Hero>

    suspend fun getHero(id: Int): Hero
}