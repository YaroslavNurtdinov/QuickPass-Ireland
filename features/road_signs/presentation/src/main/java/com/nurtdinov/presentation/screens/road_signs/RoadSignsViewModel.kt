package com.nurtdinov.presentation.screens.road_signs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurtdinov.domain.utils.RoadSignFilter
import com.nurtdinov.domain.RoadSignsUseCases
import com.nurtdinov.domain.model.RoadSignModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class RoadSignsViewModel @Inject constructor(
    private val useCases: RoadSignsUseCases
) : ViewModel() {

    private val _filter = MutableStateFlow(RoadSignFilter.ALL)
    val filter: StateFlow<RoadSignFilter> = _filter.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val stateFlow: StateFlow<RoadSignsScreenState> = _filter
        .flatMapLatest { filter ->
            useCases.getRoadSignsListUseCase(filter)
                .map { RoadSignsScreenState.Success(it) }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            RoadSignsScreenState.Loading
        )

    fun onEvent(event: RoadSignsUiEvent) {
        when (event) {
            is RoadSignsUiEvent.OnFilterSelected -> {
                _filter.value = event.filter
            }
        }
    }

    sealed class RoadSignsScreenState {
        data object Loading : RoadSignsScreenState()
        data class Success(val signs: List<RoadSignModel>) : RoadSignsScreenState()
        data class Error(val message: String) : RoadSignsScreenState()
    }
}

