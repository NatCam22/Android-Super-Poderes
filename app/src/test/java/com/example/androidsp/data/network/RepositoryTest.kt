package com.example.androidsp.data.network

import com.example.androidsp.data.Repository
import com.example.androidsp.data.RepositoryImpl
import com.example.androidsp.data.local.db.model.HeroLocal
import com.example.androidsp.data.local.db.model.toUI
import com.example.androidsp.data.network.model.HeroRemote
import com.example.androidsp.data.network.model.HeroThumbnail
import com.example.androidsp.data.network.model.toLocal
import com.example.androidsp.utils.fakes.FakeLocalDataSource
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryTest {
    private lateinit var repository: Repository
    private val localDataSource = FakeLocalDataSource()
    private val remoteDataSource: NetworkDataSource = mockk()
    @Before
    fun setUp(){
        repository = RepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `WHEN getHeroList AND not empty db THEN success list`()= runTest{
        val expectedLocal = generateLocalHeros(3)
        coEvery { localDataSource.getHeros() } returns flowOf(expectedLocal)

        val actual = repository.getHeroList()
        Assert.assertTrue(actual.toList().isNotEmpty())
    }

    @Test
    fun `WHEN getHeroList AND empty db THEN success list`()= runTest{
        localDataSource.dataBase = mutableListOf<HeroLocal>()
        val remoteHeros = generateRemoteHeros(3)
        val localHeros = remoteHeros.toLocal()
        coEvery { remoteDataSource.getHeros() } returns remoteHeros

        val actual = repository.getHeroList()

        coVerify(exactly = 1) { remoteDataSource.getHeros() }
        Truth.assertThat(actual.toList()).containsExactlyElementsIn(localHeros.toUI())
    }

}

fun generateLocalHeros(size: Int) = (0 until size).map { HeroLocal(it, "name$it", "description$it", "photo$it", favorite = true) }

fun generateRemoteHeros(size: Int) = (0 until size).map { HeroRemote(it, "name$it", "description$it", HeroThumbnail("photo$it", "jpg")) }
