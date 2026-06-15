package com.merefa.team.rickandmorty.data.repository

import com.merefa.team.rickandmorty.data.CharactersMock
import com.merefa.team.rickandmorty.domain.model.CharacterUIModel
import com.merefa.team.rickandmorty.domain.repository.RickAndMortyRepository

class RickAndMortyRepositoryImpl : RickAndMortyRepository {

    override fun getCharacters(): List<CharacterUIModel> = CharactersMock.getCharacters()
}