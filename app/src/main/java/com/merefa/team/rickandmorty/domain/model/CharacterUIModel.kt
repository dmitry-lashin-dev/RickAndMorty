package com.merefa.team.rickandmorty.domain.model

import androidx.annotation.DrawableRes

data class CharacterUIModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val origin: String,
    @DrawableRes val imageUrl: Int
)
