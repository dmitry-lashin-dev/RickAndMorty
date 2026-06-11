package com.merefa.team.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.merefa.team.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyExplorerApp()
        }
    }
}

@Composable
fun RickAndMortyExplorerApp() {
    RickAndMortyTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_24)),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.rick_and_morty_explorer),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_12)))
            Text(
                text = stringResource(R.string.learn_jetpack_compose_by_building_a_real_app_step_by_step),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_24)))
            Button(onClick = { }) {
                Text(text = stringResource(R.string.start_exploring))
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_32)))
            Text(
                text = stringResource(R.string.today_s_goal),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_4)))
            Text(
                text = stringResource(R.string.build_your_first_compose_screen),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.margin_12)))
            Button(onClick = { }) {
                Text(text = stringResource(R.string.open_favorites))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RickAndMortyTheme {
        HomeScreen()
    }
}

