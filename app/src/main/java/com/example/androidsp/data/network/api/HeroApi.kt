package com.example.androidsp.data.network.api

import com.example.androidsp.data.network.model.HeroRemote
import com.example.androidsp.data.network.model.HeroResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface HeroApi {
    @GET("/v1/public/characters")
    suspend fun getHeros(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int): HeroResponse

    @GET("/v1/public/characters/{id}?apikey={apikey}&hash={hash}&ts={ts}")
    suspend fun getHeroDetail(
        @Path("apikey") apiKey: String,
        @Path("hash") hash: String,
        @Path("ts") ts: Int,
        //The response should be fixed in order to always get a response with the RequestResponse Structure
        @Path("id") id: String): HeroRemote

    @GET("/v1/public/characters/{id}/comics?apikey={apikey}&hash={hash}&ts={ts}")
    suspend fun getHeroComics(
        @Path("apikey") apiKey: String,
        @Path("hash") hash: String,
        @Path("ts") ts: Int,
        //TODO
        @Path("id") id: String): HeroRemote

    @GET("/v1/public/characters/{id}/series?apikey={apikey}&hash={hash}&ts={ts}")
    suspend fun getHeroSeries(
        @Path("apikey") apiKey: String,
        @Path("hash") hash: String,
        @Path("ts") ts: Int,
        //TODO
        @Path("id") id: String): HeroRemote


}