package com.nurtdinov.domain

import com.nurtdinov.data_api.RoadSignsRepository
import com.nurtdinov.domain.mapper.toFavoritesDto
import com.nurtdinov.domain.model.FavoritesModel

class RemoveFromFavoritesUseCase(
    private val repository: RoadSignsRepository
) {
    suspend operator fun invoke(id: FavoritesModel) {
        repository.removeFromFavorites(id = id.toFavoritesDto())
    }
}