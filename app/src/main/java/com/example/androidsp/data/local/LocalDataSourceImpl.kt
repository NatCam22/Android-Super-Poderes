package com.example.androidsp.data.local

import com.example.androidsp.data.local.db.HeroDao
import com.example.androidsp.data.local.db.model.HeroLocal
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: HeroDao): LocalDataSource {
    override fun getHeros(): List<HeroLocal> {
        return dao.getAll()
    }

    override fun insertHeros(heros: List<HeroLocal>) {
        return dao.insertAll(heros)
    }

}