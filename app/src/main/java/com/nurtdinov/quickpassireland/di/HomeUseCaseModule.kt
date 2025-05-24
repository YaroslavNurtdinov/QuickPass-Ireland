package com.nurtdinov.quickpassireland.di

import com.nurtdinov.data_api.HomeRepository
import com.nurtdinov.domain.usecases.GetAllUseCase
import com.nurtdinov.domain.usecases.HomeUseCases
import com.nurtdinov.domain.usecases.InsertDriverTheoryData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object HomeUseCasesModule {



    @Provides
    fun provideInsertDriverTheoryData(
        repository: HomeRepository
    ): InsertDriverTheoryData {
        return InsertDriverTheoryData(repository)
    }


    @Provides
    fun provideHomeUseCases(

        insertDriverTheoryData: InsertDriverTheoryData,
    ): HomeUseCases {
        return HomeUseCases(
            insertDriverTheoryData = insertDriverTheoryData
        )
    }


}

