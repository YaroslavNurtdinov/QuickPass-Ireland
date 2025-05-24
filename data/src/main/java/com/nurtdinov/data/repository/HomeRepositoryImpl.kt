package com.nurtdinov.data.repository


import android.content.Context
import android.util.Log
import com.nurtdinov.data.local.HomeDao
import com.nurtdinov.data.local.entities.DriverTheoryEntity
import com.nurtdinov.data_api.HomeRepository
import com.nurtdinov.data_api.dto.DriverTheoryDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: HomeDao,
) : HomeRepository {

    override suspend fun insertDriverTheoryData() {
        withContext(Dispatchers.IO) {
            val questionsList = loadJsonFromAssets(context = context, "rules_of_the_road.json")

            try {
                dao.saveAllToDatabase(parseQuestionsFromJson(questionsList))
            } catch (e: Exception) {
                Log.e("DB_ERROR", "Insert error", e)
            }
            val parsedList = parseQuestionsFromJson(questionsList)
            Log.d("DB", "Parsed questions count: ${parsedList.size}")

        }

    }
}



fun loadJsonFromAssets(context: Context, filename: String): String {
    return context.assets.open(filename).bufferedReader().use { it.readText() }
}

fun parseQuestionsFromJson(json: String): List<DriverTheoryEntity> {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val type = Types.newParameterizedType(List::class.java, DriverTheoryEntity::class.java)
    val adapter = moshi.adapter<List<DriverTheoryEntity>>(type)
    return adapter.fromJson(json) ?: emptyList()
}
