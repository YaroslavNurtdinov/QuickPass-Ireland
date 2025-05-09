package com.nurtdinov.quickpassireland.di

import com.nurtdinov.data_api.HomeRepository
import com.nurtdinov.domain.usecases.GetAllUseCase
import com.nurtdinov.domain.usecases.HomeUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object HomeUseCasesModule {

    @Provides
    fun provideGetAllUseCase(
        repository: HomeRepository
    ): GetAllUseCase {
        return GetAllUseCase(repository)
    }

    @Provides
    fun provideHomeUseCases(
        getAllUseCase: GetAllUseCase
    ): HomeUseCases {
        return HomeUseCases(
            getAllUseCase = getAllUseCase
        )
    }
}

