package com.nurtdinov.domain.model

data class RoadSignModel(
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
