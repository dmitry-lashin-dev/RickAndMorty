package com.merefa.team.rickandmorty.domain.useCase

import com.merefa.team.rickandmorty.data.repository.RickAndMortyRepositoryImpl
import com.merefa.team.rickandmorty.domain.model.CharacterUIModel
import com.merefa.team.rickandmorty.domain.repository.RickAndMortyRepository

class GetCharactersUseCase(
    private val repository: RickAndMortyRepository = RickAndMortyRepositoryImpl()
) : BaseUseCase<List<CharacterUIModel>>() {

    override suspend fun executeOnBackground(): List<CharacterUIModel> {
        return repository.getCharacters()
    }
}