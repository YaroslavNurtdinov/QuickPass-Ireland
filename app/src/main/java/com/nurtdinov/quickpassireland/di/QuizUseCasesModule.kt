package com.nurtdinov.quickpassireland.di

import com.nurtdinov.data_api.QuizRepository
import com.nurtdinov.domain.use_cases.DecreaseRateUseCase
import com.nurtdinov.domain.use_cases.GenerateQuizListUseCase
import com.nurtdinov.domain.use_cases.IncreaseRateUseCase
import com.nurtdinov.domain.use_cases.QuizUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object QuizUseCasesModule {
    @Provides
    fun provideGenerateQuizListUseCase(repository: QuizRepository): GenerateQuizListUseCase {
        return GenerateQuizListUseCase(repository = repository)
    }

    @Provides
    fun provideIncreaseRateUseCase(repository: QuizRepository): IncreaseRateUseCase {
        return IncreaseRateUseCase(repository = repository)
    }

    @Provides
    fun provideDecreaseRateUseCase(repository: QuizRepository): DecreaseRateUseCase {
        return DecreaseRateUseCase(repository = repository)
    }

    @Provides
    fun provideQuizUseCases(
        generateQuizListUseCase: GenerateQuizListUseCase,
        increaseRateUseCase: IncreaseRateUseCase,
        decreaseRateUseCase: DecreaseRateUseCase
    ): QuizUseCases {
        return QuizUseCases(
            generateQuizListUseCase = generateQuizListUseCase,
            increaseRateUseCase = increaseRateUseCase,
            decreaseRateUseCase = decreaseRateUseCase,
        )
    }
}