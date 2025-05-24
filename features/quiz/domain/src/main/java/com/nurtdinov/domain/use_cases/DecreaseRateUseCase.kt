package com.nurtdinov.domain.use_cases

import com.nurtdinov.data_api.QuizRepository

class DecreaseRateUseCase(
    private val repository: QuizRepository
) {

    suspend operator fun invoke(questionId: Int) {
        repository.decreaseRate(questionId = questionId)
    }
}