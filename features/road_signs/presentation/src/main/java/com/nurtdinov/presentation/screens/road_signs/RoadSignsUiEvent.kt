package com.nurtdinov.presentation.screens.road_signs

import com.nurtdinov.domain.utils.RoadSignFilter

sealed class RoadSignsUiEvent {
    data class OnFilterSelected(val filter: RoadSignFilter): RoadSignsUiEvent()
}