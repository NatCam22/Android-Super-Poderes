package com.example.androidsp.data.network.api

import com.example.androidsp.data.network.model.ComicRemote
import com.example.androidsp.data.network.model.HeroRemote
import com.example.androidsp.data.network.model.HeroResponse
import com.example.androidsp.data.network.model.SerieRemote
import com.example.androidsp.domain.Comic
import com.example.androidsp.domain.Serie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface HeroApi {
    @GET("/v1/public/characters?limit=100")
    suspend fun getHeros(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int): HeroResponse<HeroRemote>

    @GET("/v1/public/characters/{id}")
    suspend fun getHeroDetail(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int): HeroResponse<HeroRemote>
        //The response should be fixed in order to always get a response with the RequestResponse Structure


    @GET("/v1/public/characters/{id}/comics")
    suspend fun getHeroComics(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int
        ): HeroResponse<ComicRemote>

    @GET("/v1/public/characters/{id}/series")
    suspend fun getHeroSeries(
        @Path("id") id: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int
        ): HeroResponse<SerieRemote>
    


}