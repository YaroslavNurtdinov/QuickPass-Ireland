package com.nurtdinov.domain.use_cases

import com.nurtdinov.data_api.QuizRepository

class IncreaseRateUseCase(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(questionId: Int) {
        repository.increaseRate(questionId = questionId)
    }
}