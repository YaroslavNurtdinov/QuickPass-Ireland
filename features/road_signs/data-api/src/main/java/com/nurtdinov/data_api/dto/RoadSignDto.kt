package com.nurtdinov.data_api.dto

data class RoadSignDto(
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
