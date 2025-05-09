package com.nurtdinov.domain.mapper

import com.nurtdinov.data_api.dto.DriverTheoryDTO
import com.nurtdinov.domain.model.DriverTheoryModel

fun DriverTheoryDTO.toDriverTheoryModel(): DriverTheoryModel {
    return DriverTheoryModel(
        id = id,
        question = question
    )
}