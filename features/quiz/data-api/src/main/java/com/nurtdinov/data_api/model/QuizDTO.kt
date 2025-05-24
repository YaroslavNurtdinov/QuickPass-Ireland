package com.nurtdinov.data_api.model

data class QuizDTO(
    val id: Int,
    val category: String,
    val question: String,
    val image: String,
    val answers: List<String>,
    val correctAnswer: String,
    val explanation: String,
    val favorites: Boolean,
    val rate: Int,
)