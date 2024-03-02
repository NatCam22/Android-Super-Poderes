package com.example.androidsp.domain

data class Hero(
    override val id: Int,
    override val name: String,
    override val photo: String,
    val description: String,
    val favorite: Boolean
): HeroLike