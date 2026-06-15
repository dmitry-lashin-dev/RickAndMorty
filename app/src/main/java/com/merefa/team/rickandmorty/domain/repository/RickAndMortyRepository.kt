package com.merefa.team.rickandmorty.domain.repository

import com.merefa.team.rickandmorty.domain.model.CharacterUIModel

interface RickAndMortyRepository {

    fun getCharacters(): List<CharacterUIModel>
}