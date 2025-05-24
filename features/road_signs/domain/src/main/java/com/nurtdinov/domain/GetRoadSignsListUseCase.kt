package com.nurtdinov.domain

import com.nurtdinov.data_api.RoadSignsRepository
import com.nurtdinov.domain.mapper.toRoadSignModel
import com.nurtdinov.domain.model.RoadSignModel
import com.nurtdinov.domain.utils.RoadSignConstants
import com.nurtdinov.domain.utils.RoadSignFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class GetRoadSignsListUseCase(
    private val repository: RoadSignsRepository
) {
    operator fun invoke(filter: RoadSignFilter): Flow<List<RoadSignModel>> {
        return repository.getRoadSignsList()
            .map { entities ->
                entities.map { it.toRoadSignModel() }
                    .filter { it.image.isNotEmpty() }
                    .filter { sign ->
                        when (filter) {
                            RoadSignFilter.ALL -> true
                            RoadSignFilter.REGULATORY -> sign.image.startsWith(RoadSignConstants.REGULATORY_PREFIX)
                            RoadSignFilter.WARNING -> sign.image.startsWith(RoadSignConstants.WARNING_PREFIX)
                            RoadSignFilter.WORKER -> sign.image.startsWith(RoadSignConstants.WORKER_PREFIX)
                            RoadSignFilter.MOTORWAY -> sign.image.startsWith(RoadSignConstants.MOTORWAY_PREFIX)
                            RoadSignFilter.HAND_SIGNAL -> sign.image.startsWith(RoadSignConstants.HAND_SIGNAL_PREFIX)
                            RoadSignFilter.ILLUSTRATION -> sign.image.startsWith(RoadSignConstants.ILLUSTRATION_PREFIX)
                            RoadSignFilter.TRAFFIC_LIGHTS -> sign.image.startsWith(RoadSignConstants.TRAFFIC_LIGHTS_PREFIX)
                        }
                    }
                    .sortedWith(
                        compareBy<RoadSignModel> {
                            when {
                                it.image.startsWith(RoadSignConstants.REGULATORY_PREFIX) -> 0
                                it.image.startsWith(RoadSignConstants.WARNING_PREFIX) -> 1
                                it.image.startsWith(RoadSignConstants.WORKER_PREFIX) -> 2
                                it.image.startsWith(RoadSignConstants.MOTORWAY_PREFIX) -> 3
                                it.image.startsWith(RoadSignConstants.HAND_SIGNAL_PREFIX) -> 4
                                it.image.startsWith(RoadSignConstants.ILLUSTRATION_PREFIX) -> 5
                                it.image.startsWith(RoadSignConstants.TRAFFIC_LIGHTS_PREFIX) -> 6
                                else -> 7
                            }
                        }.thenBy { it.image }
                    )
            }
    }
}
