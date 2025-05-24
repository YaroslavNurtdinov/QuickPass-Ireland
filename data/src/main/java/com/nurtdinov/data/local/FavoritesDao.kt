package com.nurtdinov.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nurtdinov.data.local.entities.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

/*    @Query("SELECT * FROM favorites_table")
    fun getAllFavorites(): Flow<List<FavoritesEntity>>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favorite: FavoritesEntity)

    @Delete
    suspend fun removeFromFavorites(favorite: FavoritesEntity)

    @Query("SELECT questionId FROM favorites_table")
    fun getAllFavoriteIdsFlow(): Flow<List<Int>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_table WHERE questionId = :id)")
    suspend fun isFavorite(id: Int): Boolean

}