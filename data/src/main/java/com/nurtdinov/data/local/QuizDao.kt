package com.nurtdinov.data.local

import androidx.room.Dao
import androidx.room.Query
import com.nurtdinov.data.local.entities.DriverTheoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface QuizDao {

    @Query("SELECT * FROM driver_theory_table")
    fun getAllQuestions(): Flow<List<DriverTheoryEntity>>

    @Query("SELECT * FROM driver_theory_table ORDER BY rate ASC, RANDOM() LIMIT 40")
    fun generateQuizList(): Flow<List<DriverTheoryEntity>>

    @Query("UPDATE driver_theory_table SET rate = rate + 1 WHERE id = :questionId")
    suspend fun increaseRate(questionId: Int)

    @Query("UPDATE driver_theory_table SET rate = rate - 1 WHERE id = :questionId AND rate > 0")
    suspend fun decreaseRate(questionId: Int)
}