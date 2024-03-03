package com.example.androidsp.utils

import com.example.androidsp.data.local.db.model.HeroLocal
import com.example.androidsp.data.network.model.HeroRemote
import com.example.androidsp.data.network.model.HeroThumbnail
import com.example.androidsp.domain.Hero

class Generators {
    fun generateUIHeros(size: Int) = (0 until size).map { Hero(it,"name$it",  "photo$it", "$it", true) }

    fun generateLocalHeros(size: Int) = (0 until size).map { HeroLocal(it, "name$it", "description$it", "photo$it", favorite = true) }

    fun generateRemoteHeros(size: Int) = (0 until size).map { HeroRemote(it, "name$it", "description$it", HeroThumbnail("photo$it", "jpg")) }

}