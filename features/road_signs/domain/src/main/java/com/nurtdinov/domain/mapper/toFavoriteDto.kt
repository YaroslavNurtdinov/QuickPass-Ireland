package com.nurtdinov.domain.mapper

import com.nurtdinov.data_api.dto.FavoritesDto
import com.nurtdinov.domain.model.FavoritesModel


fun FavoritesModel.toFavoritesDto(): FavoritesDto {
    return FavoritesDto(
        questionId = questionId
    )
}