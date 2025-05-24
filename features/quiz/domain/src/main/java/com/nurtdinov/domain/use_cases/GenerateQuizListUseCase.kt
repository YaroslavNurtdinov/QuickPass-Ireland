package com.nurtdinov.domain.use_cases

import com.nurtdinov.data_api.QuizRepository
import com.nurtdinov.domain.mapper.toQuizModel
import com.nurtdinov.domain.model.QuizModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GenerateQuizListUseCase(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(): Flow<List<QuizModel>> {
        return repository.generateQuizList().map { questions ->
            questions.map { question ->
                question.toQuizModel()
            }
        }
    }

}