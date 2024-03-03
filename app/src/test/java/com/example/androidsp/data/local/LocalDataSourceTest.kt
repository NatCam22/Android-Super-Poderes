package com.example.androidsp.data.local

import com.example.androidsp.data.local.db.HeroDao
import com.example.androidsp.data.local.db.model.HeroLocal
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {
    private lateinit var localDataSource: LocalDataSource

    private lateinit var dao: HeroDao

    @Before
    fun setUp(){
        dao = mockk()
        localDataSource = LocalDataSourceImpl(dao)
    }

    @Test
    fun `WHEN getHerosCount THEN count(Heros) equals count`(){
        every { dao.getHerosCount() } returns 10
        Assert.assertEquals(10, localDataSource.getHerosCount())
    }

    @Test
    fun `WHEN getFavorite THEN success boolean, true`(){
        every { dao.getFavorite(123) } returns true
        val fav = localDataSource.getFavorite(123)
        Assert.assertEquals(true, fav)
    }

    @After
    fun tearDown(){

    }
}