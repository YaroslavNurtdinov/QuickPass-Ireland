package com.nurtdinov.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_theory_table")
data class DriverTheoryEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val category: String,
    val question: String,
    val image: String,
    val answers: List<String>,
    val correctAnswer: String,
    val explanation: String,
    val favorites: Boolean = false,
    val rate: Int = 10,
)
