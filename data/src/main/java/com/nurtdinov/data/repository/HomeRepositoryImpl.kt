package com.nurtdinov.data.repository


import com.nurtdinov.data_api.HomeRepository
import com.nurtdinov.data_api.dto.DriverTheoryDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl@Inject constructor() : HomeRepository {
    override fun getAll(): Flow<List<DriverTheoryDTO>> = flow {
        val quetions = listOf<DriverTheoryDTO>(
            DriverTheoryDTO(
                id = 0,
                question = "How are u?"
            ),
            DriverTheoryDTO(
                id = 1,
                question = "How are k?"
            ),
            DriverTheoryDTO(
                id = 2,
                question = "How are o?"
            ),
        )
        emit(quetions)
    }
}