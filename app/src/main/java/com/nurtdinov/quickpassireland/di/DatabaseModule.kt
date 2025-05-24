package com.nurtdinov.quickpassireland.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.nurtdinov.data.local.QuickPassDatabase
import com.nurtdinov.data.repository.HomeRepositoryImpl
import com.nurtdinov.data.repository.QuizRepositoryImpl
import com.nurtdinov.data.repository.RoadSignsRepositoryImpl
import com.nurtdinov.data_api.HomeRepository
import com.nurtdinov.data_api.QuizRepository
import com.nurtdinov.data_api.RoadSignsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): QuickPassDatabase {
        return Room.databaseBuilder(
            application,
            QuickPassDatabase::class.java,
            "quick_pass_database",
        ).build()
    }

    @Provides
    @Singleton
    fun provideHomeRepositoryImpl(
        @ApplicationContext context: Context,
        db: QuickPassDatabase
    ): HomeRepository {
        return HomeRepositoryImpl(context = context, dao = db.homeDao())
    }


    @Provides
    @Singleton
    fun provideQuizRepositoryImpl(
        db: QuickPassDatabase
    ): QuizRepository {
        return QuizRepositoryImpl(quizDao = db.quizDao())
    }

    @Singleton
    @Provides
    fun provideRoadSignsRepositoryImpl(
        db: QuickPassDatabase
    ): RoadSignsRepository {
        return RoadSignsRepositoryImpl(dao = db.roadSignDao(), favoritesDao = db.favoritesDao())
    }
}