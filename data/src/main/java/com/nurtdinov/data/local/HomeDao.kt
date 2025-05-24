package com.nurtdinov.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nurtdinov.data.local.entities.DriverTheoryEntity


@Dao
interface HomeDao {

    @Insert(entity = DriverTheoryEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAllToDatabase(list: List<DriverTheoryEntity>)

}