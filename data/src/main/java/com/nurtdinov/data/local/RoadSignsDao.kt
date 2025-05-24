package com.nurtdinov.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nurtdinov.data.local.entities.DriverTheoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RoadSignsDao {
    @Query("SELECT * FROM driver_theory_table WHERE image != ''")
    fun getRoadSignsList(): Flow<List<DriverTheoryEntity>>

    @Query("SELECT * FROM driver_theory_table WHERE id = :id")
    suspend fun getSignById(id: Int): DriverTheoryEntity

}