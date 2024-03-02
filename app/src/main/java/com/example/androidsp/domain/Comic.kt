package com.example.androidsp.domain

data class Comic(
    override val id: Int,
    override val name: String,
    override val photo: String
): HeroLike