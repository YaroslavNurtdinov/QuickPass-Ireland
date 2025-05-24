package com.nurtdinov.data_api

import com.nurtdinov.data_api.model.QuizDTO
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    suspend fun generateQuizList(): Flow<List<QuizDTO>>

    suspend fun increaseRate(questionId: Int)

    suspend fun decreaseRate(questionId: Int)
}