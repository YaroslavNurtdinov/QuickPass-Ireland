package com.nurtdinov.domain.usecases

import com.nurtdinov.data_api.HomeRepository

class InsertDriverTheoryData (
    private val homeRepository: HomeRepository
) {
    suspend fun insertDriverTheoryData(){
        homeRepository.insertDriverTheoryData()
    }
}