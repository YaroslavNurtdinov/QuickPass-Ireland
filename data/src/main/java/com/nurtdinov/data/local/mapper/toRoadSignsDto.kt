package com.nurtdinov.data.local.mapper

import com.nurtdinov.data.local.entities.DriverTheoryEntity
import com.nurtdinov.data_api.dto.RoadSignDto

fun DriverTheoryEntity.toRoadSignsDto(): RoadSignDto {
    return RoadSignDto(
        id = id,
        category = category,
        question = question,
        image = image,
        answers = answers,
        correctAnswer = correctAnswer,
        explanation = explanation,
        favorites = favorites,
        rate = rate,
    )
}