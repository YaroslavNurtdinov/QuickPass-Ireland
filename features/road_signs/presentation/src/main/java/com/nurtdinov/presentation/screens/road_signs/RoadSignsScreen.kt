package com.nurtdinov.presentation.screens.road_signs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurtdinov.domain.utils.RoadSignFilter
import com.nurtdinov.presentation.screens.road_signs.component.RoadSignListItem


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RoadSignsScreen(onNavBackClick: () -> Unit, onItemClick: (Int) -> Unit) {
    val viewModel = hiltViewModel<RoadSignsViewModel>()
    val state = viewModel.stateFlow.collectAsState()
    val currentFilter by remember { derivedStateOf { viewModel.filter } }

    RoadSignsContent(
        state = state.value,
        selectedFilter = currentFilter.value,
        onFilterSelected = { filter ->
            viewModel.onEvent(RoadSignsUiEvent.OnFilterSelected(filter))
        },
        onNavBackClick = onNavBackClick,
        onItemClick = { id ->
            onItemClick(id)
        }
    )
}


@Composable
fun RoadSignsContent(
    state: RoadSignsViewModel.RoadSignsScreenState,
    selectedFilter: RoadSignFilter,
    onFilterSelected: (RoadSignFilter) -> Unit,
    onNavBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    onNavBackClick()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(RoadSignFilter.entries) { filter ->
                        val isSelected = filter == selectedFilter

                        FilterChip(
                            selected = isSelected,
                            onClick = { onFilterSelected(filter) },
                            label = {
                                Text(
                                    text = filter.name
                                        .lowercase()
                                        .replace("_", " ")
                                        .replaceFirstChar { it.uppercaseChar() }, fontSize = 26.sp
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }

            }
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            when (state) {
                is RoadSignsViewModel.RoadSignsScreenState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is RoadSignsViewModel.RoadSignsScreenState.Success -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(items = state.signs) { index, item ->
                            RoadSignListItem(
                                id = index,
                                question = item.question,
                                correctAnswer = item.correctAnswer,
                                imageName = item.image,
                                onClick = { onItemClick(item.id) },
                                favorites = item.favorites
                            )
                        }
                    }
                }

                is RoadSignsViewModel.RoadSignsScreenState.Error -> {
                    Text(text = state.message.toString())
                }
            }
        }
    }


}