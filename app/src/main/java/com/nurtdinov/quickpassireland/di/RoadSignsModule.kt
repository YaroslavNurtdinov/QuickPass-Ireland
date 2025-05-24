package com.nurtdinov.quickpassireland.di

import com.nurtdinov.data_api.RoadSignsRepository
import com.nurtdinov.domain.AddToFavoritesUseCase
import com.nurtdinov.domain.GetRoadSignsListUseCase
import com.nurtdinov.domain.GetSignByIdUseCase
import com.nurtdinov.domain.RemoveFromFavoritesUseCase
import com.nurtdinov.domain.RoadSignsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RoadSignsModule {

    @Provides
    fun provideGetRoadSignsListUseCase(repository: RoadSignsRepository): GetRoadSignsListUseCase {
        return GetRoadSignsListUseCase(
            repository = repository,
        )
    }

    @Provides
    fun provideGetSignByIdUseCase(repository: RoadSignsRepository): GetSignByIdUseCase {
        return GetSignByIdUseCase(
            repository = repository,
        )
    }

    @Provides
    fun provideAddToFavoriteUseCase(repository: RoadSignsRepository): AddToFavoritesUseCase {
        return AddToFavoritesUseCase(repository = repository)
    }

    @Provides
    fun provideRemoveFromFavoriteUseCase(repository: RoadSignsRepository): RemoveFromFavoritesUseCase {
        return RemoveFromFavoritesUseCase(repository = repository)
    }

    @Provides
    fun provideRoadSignsUseCases(
        getRoadSignsListUseCase: GetRoadSignsListUseCase,
        getSignByIdUseCase: GetSignByIdUseCase,
        addToFavoritesUseCase: AddToFavoritesUseCase,
        removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    ): RoadSignsUseCases {
        return RoadSignsUseCases(
            getRoadSignsListUseCase = getRoadSignsListUseCase,
            getSignByIdUseCase = getSignByIdUseCase,
            addToFavoritesUseCase = addToFavoritesUseCase,
            removeFromFavoritesUseCase = removeFromFavoritesUseCase,
        )
    }
}