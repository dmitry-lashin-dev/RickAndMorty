package com.merefa.team.rickandmorty.data

import com.merefa.team.rickandmorty.R
import com.merefa.team.rickandmorty.domain.model.CharacterUIModel

object CharactersMock {

    fun getCharacters(): List<CharacterUIModel> = listOf(
        CharacterUIModel(1, "Rick Sanchez", "Alive", "Human", "Earth", R.drawable.ic_rick),
        CharacterUIModel(2, "Morty Smith", "Alive", "Human", "Earth", R.drawable.ic_morty),
        CharacterUIModel(3, "Julius Caesar", "Dead", "Human", "Earth", R.drawable.ic_caesar),
        CharacterUIModel(4, "Unknown Smith", "Unknown", "Human", "Earth", R.drawable.ic_unknowen),
        CharacterUIModel(5, "Bradley Pitt", "Alive", "Human", "Earth", R.drawable.ic_pitt),
    )
}