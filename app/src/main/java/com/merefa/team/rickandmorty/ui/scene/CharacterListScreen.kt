package com.merefa.team.rickandmorty.ui.scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.merefa.team.rickandmorty.R
import com.merefa.team.rickandmorty.data.CharactersMock
import com.merefa.team.rickandmorty.domain.model.CharacterUIModel
import com.merefa.team.rickandmorty.ui.components.CharacterCard
import com.merefa.team.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterListScreen(
    characters: List<CharacterUIModel>,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(dimensionResource(R.dimen.padding_16)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_12)),
        ) {
            items(
                items = characters,
                key = { it.id }
            ) { character ->
                CharacterCard(character = character)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview() {
    RickAndMortyTheme {
        CharacterListScreen(characters = CharactersMock.getCharacters())
    }
}