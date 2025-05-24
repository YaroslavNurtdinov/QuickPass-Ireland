package com.nurtdinov.domain.use_cases

data class QuizUseCases(
    val generateQuizListUseCase: GenerateQuizListUseCase,
    val increaseRateUseCase: IncreaseRateUseCase,
    val decreaseRateUseCase: DecreaseRateUseCase,
)
