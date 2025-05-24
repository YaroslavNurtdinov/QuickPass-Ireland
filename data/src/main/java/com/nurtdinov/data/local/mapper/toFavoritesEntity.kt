package com.nurtdinov.data.local.mapper

import com.nurtdinov.data.local.entities.FavoritesEntity
import com.nurtdinov.data_api.dto.FavoritesDto

fun FavoritesDto.toFavoritesEntity(): FavoritesEntity {
    return FavoritesEntity(
        questionId = questionId,
    )
}