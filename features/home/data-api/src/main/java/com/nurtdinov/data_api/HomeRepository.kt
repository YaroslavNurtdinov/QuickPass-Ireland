package com.nurtdinov.data_api

import com.nurtdinov.data_api.dto.DriverTheoryDTO
import kotlinx.coroutines.flow.Flow

interface HomeRepository {


    suspend fun insertDriverTheoryData()

}