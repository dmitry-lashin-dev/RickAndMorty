package com.merefa.team.rickandmorty.domain.model

data class Character(
    val name: String,
    val species: String,
    val status: String,
    val origin: String,
    val imageResId: Int,
)
