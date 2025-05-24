package com.nurtdinov.data.repository

import com.nurtdinov.data.local.FavoritesDao
import com.nurtdinov.data.local.RoadSignsDao
import com.nurtdinov.data.local.mapper.toFavoritesEntity
import com.nurtdinov.data.local.mapper.toRoadSignsDto
import com.nurtdinov.data_api.RoadSignsRepository
import com.nurtdinov.data_api.dto.FavoritesDto
import com.nurtdinov.data_api.dto.RoadSignDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class RoadSignsRepositoryImpl @Inject constructor(
    private val dao: RoadSignsDao,
    private val favoritesDao: FavoritesDao,
) : RoadSignsRepository {
    override fun getRoadSignsList(): Flow<List<RoadSignDto>> {
        return combine(
            dao.getRoadSignsList(),
            favoritesDao.getAllFavoriteIdsFlow()
        ) { signs, favoriteIds ->
            val favoriteSet = favoriteIds.toSet()
            signs.map { signEntity ->
                val dto = signEntity.toRoadSignsDto()
                dto.copy(favorites = favoriteSet.contains(signEntity.id))
            }
        }
    }

    override suspend fun getSignById(id: Int): RoadSignDto {
        val sign = dao.getSignById(id)
        val isFavorite = favoritesDao.isFavorite(sign.id)
        return sign.toRoadSignsDto().copy(favorites = isFavorite)
    }

    override suspend fun addToFavorites(id: FavoritesDto) {
        favoritesDao.addToFavorites(favorite = id.toFavoritesEntity())
    }

    override suspend fun removeFromFavorites(id: FavoritesDto) {
        favoritesDao.removeFromFavorites(favorite = id.toFavoritesEntity())
    }
}