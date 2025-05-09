package com.nurtdinov.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nurtdinov.presentation.components.HomeOptionItem
import com.nurtdinov.presentation.components.HomeOption
import com.nurtdinov.presentation.components.HomeOptions
import kotlinx.collections.immutable.ImmutableList

@Composable
fun HomeScreen(navController: NavController) {

    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.stateFlow.collectAsState().value
    HomeContent(
        homeOptions = HomeOptions,
        onNavClick = { route ->
            navController.navigate(route)
        },
    )

}


@Composable
fun HomeContent(
    homeOptions: ImmutableList<HomeOption>,
    onNavClick: (Any) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )

        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.home_plate),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        homeOptions.forEach { option ->
            HomeOptionItem(
                item = option,
                onClick = {
                    onNavClick(option.route)
                },
            )
        }
    }
}


