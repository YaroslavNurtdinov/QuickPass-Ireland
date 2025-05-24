package com.nurtdinov.presentation.screens.sign_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurtdinov.domain.RoadSignsUseCases
import com.nurtdinov.domain.model.FavoritesModel
import com.nurtdinov.domain.model.RoadSignModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = SignDetailsViewModel.Factory::class)
class SignDetailsViewModel @AssistedInject constructor(
    @Assisted private val id: Int,
    private val useCases: RoadSignsUseCases,
) : ViewModel() {
    private val _state = mutableStateOf<SignDetailScreenState>(SignDetailScreenState.Loading)
    val state: State<SignDetailScreenState> = _state

    init {
        getSignById()
    }

    private fun getSignById() {
        viewModelScope.launch {
            try {
                val sign = useCases.getSignByIdUseCase.invoke(id = id)
                _state.value = SignDetailScreenState.Success(sign)
            } catch (e: Exception) {
                _state.value = SignDetailScreenState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun uiEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnFavoritesClick -> {
                viewModelScope.launch {
                    val model = event.sign

                    // Створюємо FavoritesModel з повної моделі
                    val favorite = FavoritesModel(questionId = model.id)

                    if (model.favorites) {
                        useCases.removeFromFavoritesUseCase.invoke(favorite)
                    } else {
                        useCases.addToFavoritesUseCase.invoke(favorite)
                    }

                    // Оновлюємо після натискання, щоб UI оновився
                    getSignById()
                }
            }
        }
    }


    sealed class SignDetailScreenState() {
        data object Loading : SignDetailScreenState()
        data class Success(val sign: RoadSignModel) : SignDetailScreenState()
        data class Error(val message: String) : SignDetailScreenState()
    }

    sealed class UiEvent {
        data class OnFavoritesClick(val sign: RoadSignModel) : UiEvent()
    }

    @AssistedFactory
    interface Factory {
        fun create(index: Int): SignDetailsViewModel
    }
}