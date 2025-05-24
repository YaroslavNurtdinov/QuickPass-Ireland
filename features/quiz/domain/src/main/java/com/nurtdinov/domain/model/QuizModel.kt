package com.nurtdinov.domain.model

data class QuizModel(
    val id: Int,
    val question: String,
    val image: String,
    val answers: List<String>,
    val correctAnswer: String,
    val explanation: String,
    val favorites: Boolean,
    val rate: Int,
)
