package com.nurtdinov.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nurtdinov.data.local.converters.Converters
import com.nurtdinov.data.local.entities.DriverTheoryEntity
import com.nurtdinov.data.local.entities.FavoritesEntity

@Database(
    entities = [DriverTheoryEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class QuickPassDatabase : RoomDatabase() {

    abstract fun homeDao(): HomeDao
    abstract fun quizDao(): QuizDao
    abstract fun roadSignDao(): RoadSignsDao
    abstract fun favoritesDao(): FavoritesDao

}
