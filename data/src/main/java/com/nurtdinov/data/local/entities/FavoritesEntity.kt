package com.nurtdinov.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true) val questionId: Int,
)
