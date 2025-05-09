package com.nurtdinov.domain.usecases

import com.nurtdinov.data_api.HomeRepository
import com.nurtdinov.domain.mapper.toDriverTheoryModel
import com.nurtdinov.domain.model.DriverTheoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllUseCase(
    private val homeRepository: HomeRepository
) {
    fun getAll(): Flow<List<DriverTheoryModel>> {
        return homeRepository.getAll().map { it.map { it.toDriverTheoryModel() } }
    }
}