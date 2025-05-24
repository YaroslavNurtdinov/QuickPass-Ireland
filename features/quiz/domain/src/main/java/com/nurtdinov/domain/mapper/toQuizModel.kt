package com.nurtdinov.domain.mapper

import com.nurtdinov.domain.model.QuizModel
import com.nurtdinov.data_api.model.QuizDTO

fun QuizDTO.toQuizModel(): QuizModel {
    return QuizModel(
        id = id,
        question = question,
        image = image,
        answers = answers,
        correctAnswer = correctAnswer,
        explanation = explanation,
        favorites = favorites,
        rate = rate,
    )
}