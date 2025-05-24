package com.nurtdinov.data.repository

import com.nurtdinov.data.local.QuizDao
import com.nurtdinov.data.local.mapper.toQuizDTO
import com.nurtdinov.data_api.QuizRepository
import com.nurtdinov.data_api.model.QuizDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val quizDao: QuizDao
) : QuizRepository {

    override suspend fun generateQuizList(): Flow<List<QuizDTO>> {
        return quizDao.generateQuizList()
            .map { questions ->
                questions.map { question ->
                    question.toQuizDTO()
                }
            }
    }

    override suspend fun increaseRate(questionId: Int) {
        quizDao.increaseRate(questionId)
    }

    override suspend fun decreaseRate(questionId: Int) {
        quizDao.decreaseRate(questionId)
    }
}