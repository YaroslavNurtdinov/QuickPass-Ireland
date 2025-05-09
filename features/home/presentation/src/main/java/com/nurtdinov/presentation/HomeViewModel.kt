package com.nurtdinov.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurtdinov.domain.model.DriverTheoryModel
import com.nurtdinov.domain.usecases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {


    val stateFlow: StateFlow<HomeStateScreen> = homeUseCases.getAllUseCase.getAll()
        .map { HomeStateScreen.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = HomeStateScreen.Loading,
        )


    sealed class HomeStateScreen() {
        data object Loading : HomeStateScreen()
        data class Success(val questions: List<DriverTheoryModel>) : HomeStateScreen()
        data class Error(val error: DriverTheoryModel) : HomeStateScreen()
    }
}



