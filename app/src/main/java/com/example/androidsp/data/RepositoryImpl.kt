package com.example.androidsp.data

import android.util.Log
import com.example.androidsp.data.local.LocalDataSource
import com.example.androidsp.data.local.db.model.HeroLocal
import com.example.androidsp.data.local.db.model.toUI
import com.example.androidsp.data.network.NetworkDataSource
import com.example.androidsp.data.network.model.toLocal
import com.example.androidsp.data.network.model.toUI
import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Hero
import com.example.androidsp.domain.Serie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val networkDataSource: NetworkDataSource
): Repository{
    override suspend fun getHeroList(): Flow<List<Hero>> {

        return if (localDataSource.getHerosCount() == 0){
            val remoteHeros = networkDataSource.getHeros()
            //cast heroRemote to heroLocal
            val localHeros = remoteHeros.toLocal()
            localDataSource.insertHeros(localHeros)
            //cast heroLocal to hero
            localDataSource.getHeros().map{
                it.toUI()
            }
        } else{
            //cast heroLocal to hero
            localDataSource.getHeros().map{
                it.toUI()
            }
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

    override suspend fun likeHero(hero: Hero): Boolean{
        val fav1 = localDataSource.getFavorite(hero.id)
        Log.d("FAVORITO1", fav1.toString())
        //localDataSource.setFavorite(!localDataSource.getFavorite(hero.id), hero.id)
        localDataSource.updateHero(HeroLocal(hero.id, hero.name, hero.description, hero.photo, !fav1))
        val fav2 = localDataSource.getFavorite(hero.id)
        Log.d("FAVORITO2", fav2.toString())
        return fav2
    }
}