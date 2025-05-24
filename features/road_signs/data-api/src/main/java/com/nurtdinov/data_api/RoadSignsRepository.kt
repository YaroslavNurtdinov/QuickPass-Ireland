package com.nurtdinov.data_api

import com.nurtdinov.data_api.dto.FavoritesDto
import com.nurtdinov.data_api.dto.RoadSignDto
import kotlinx.coroutines.flow.Flow

interface RoadSignsRepository {
    fun getRoadSignsList(): Flow<List<RoadSignDto>>

    suspend fun getSignById(id: Int): RoadSignDto

    suspend fun addToFavorites(id: FavoritesDto)

    suspend fun removeFromFavorites(id: FavoritesDto)
}