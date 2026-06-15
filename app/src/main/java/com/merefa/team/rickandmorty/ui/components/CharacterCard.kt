package com.merefa.team.rickandmorty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merefa.team.rickandmorty.R
import com.merefa.team.rickandmorty.domain.model.CharacterUIModel
import com.merefa.team.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterCard(
    character: CharacterUIModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(R.dimen.corner_radius_16)),
        elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_4dp))
    ) {
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_12)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(character.imageResId),
                contentDescription = character.name,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(dimensionResource(R.dimen.corner_radius_12))),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(dimensionResource(R.dimen.margin_12)))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.Circle,
                        contentDescription = character.status,
                        tint = getStatusColor(character.status),
                        modifier = Modifier.size(12.dp)
                    )
                }

                Text(
                    text = "${stringResource(R.string.status)}: ${character.status}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "${stringResource(R.string.species)}: ${character.species}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = character.origin,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

private fun getStatusColor(status: String): Color {
    return when (status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    RickAndMortyTheme {
        CharacterCard(
            character = CharacterUIModel(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "Human",
                imageResId = R.drawable.ic_rick,
                origin = "Earth"
            ),
            modifier = Modifier
        )
    }
}