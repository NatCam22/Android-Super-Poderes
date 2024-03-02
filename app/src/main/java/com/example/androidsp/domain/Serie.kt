package com.example.androidsp.domain

data class Serie(
    override val id: Int,
    override val name: String,
    override val photo: String
): HeroLike