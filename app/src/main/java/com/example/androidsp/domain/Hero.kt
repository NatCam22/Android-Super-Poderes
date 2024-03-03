package com.example.androidsp.domain

import com.example.androidsp.data.local.db.model.HeroLocal

data class Hero(
    override val id: Int,
    override val name: String,
    override val photo: String,
    val description: String,
    val favorite: Boolean
): HeroLike
