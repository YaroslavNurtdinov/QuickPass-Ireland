package com.nurtdinov.domain

data class RoadSignsUseCases(
    val getRoadSignsListUseCase: GetRoadSignsListUseCase,
    val getSignByIdUseCase: GetSignByIdUseCase,
    val addToFavoritesUseCase: AddToFavoritesUseCase,
    val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
)
