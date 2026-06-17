package com.merefa.team.rickandmorty.ui.scene

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.merefa.team.rickandmorty.R
import com.merefa.team.rickandmorty.data.CharactersMock
import com.merefa.team.rickandmorty.domain.model.CharacterUIModel
import com.merefa.team.rickandmorty.ui.components.CharacterCard
import com.merefa.team.rickandmorty.ui.components.CharacterSearchBar
import com.merefa.team.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterListScreen(
    characters: List<CharacterUIModel>,
    modifier: Modifier = Modifier,
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val filteredCharacters = characters
        .filter { it.name.contains(other = searchQuery, ignoreCase = true) }
    Surface(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CharacterSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_16))
            )

            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.padding_12)))

            if (filteredCharacters.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.no_characters_found),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.padding_16)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_12)),
                ) {
                    items(
                        items = filteredCharacters,
                        key = { it.id }
                    ) { character ->
                        CharacterCard(character = character)
                    }
                }
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