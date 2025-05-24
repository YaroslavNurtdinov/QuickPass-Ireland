package com.nurtdinov.domain

import com.nurtdinov.data_api.RoadSignsRepository
import com.nurtdinov.domain.mapper.toRoadSignModel
import com.nurtdinov.domain.model.RoadSignModel

class GetSignByIdUseCase(
    private val repository: RoadSignsRepository,
) {
    suspend operator fun invoke(id: Int): RoadSignModel {
        return repository.getSignById(id).toRoadSignModel()
    }
}