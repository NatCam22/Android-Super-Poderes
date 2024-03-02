package com.example.androidsp.data

import com.example.androidsp.data.local.LocalDataSource
import com.example.androidsp.data.local.db.model.toUI
import com.example.androidsp.data.network.NetworkDataSource
import com.example.androidsp.data.network.model.toLocal
import com.example.androidsp.data.network.model.toUI
import com.example.androidsp.domain.Hero
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
}