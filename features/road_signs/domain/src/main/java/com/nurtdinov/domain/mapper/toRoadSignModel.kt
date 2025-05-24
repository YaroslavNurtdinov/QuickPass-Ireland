package com.nurtdinov.domain.mapper

import com.nurtdinov.data_api.dto.RoadSignDto
import com.nurtdinov.domain.model.RoadSignModel

fun RoadSignDto.toRoadSignModel(): RoadSignModel {
    return RoadSignModel(
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