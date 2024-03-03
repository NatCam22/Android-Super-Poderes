package com.example.androidsp.data

import com.example.androidsp.data.local.LocalDataSource
import com.example.androidsp.data.local.db.model.toUI
import com.example.androidsp.data.network.NetworkDataSource
import com.example.androidsp.data.network.model.toLocal
import com.example.androidsp.data.network.model.toUI
import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.Serie
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
): Repository{
    override suspend fun getHeroList(): List<Hero> {

        val localHeros = localDataSource.getHeros()
        if (localHeros.isEmpty()){
            val remoteHeros = networkDataSource.getHeros()
            //cast heroRemote to heroLocal
            val localHeros = remoteHeros.toLocal()
            localDataSource.insertHeros(localHeros)
            val herosAgain = localDataSource.getHeros()
            //cast heroLocal to hero
            return herosAgain.toUI()
        } else{
            //cast heroLocal to hero
            return localHeros.toUI()
        }

    }

    override suspend fun getHero(id: Int): Hero{
        val remoteHero = networkDataSource.getHero(id)
        return remoteHero.toUI()
    }

    override suspend fun getSeries(id: Int): List<Serie>{
        val remoteSeries = networkDataSource.getSeries(id)
        return remoteSeries.toUI()
    }

    override suspend fun getComics(id: Int): List<Comic>{
        val remoteComics = networkDataSource.getComics(id)
        return remoteComics.toUI()
    }
}