package com.nurtdinov.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nurtdinov.domain.model.QuizModel
import com.nurtdinov.domain.use_cases.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.set

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val useCases: QuizUseCases
) : ViewModel() {

    private val _screenState = MutableStateFlow<QuizScreenState>(QuizScreenState.Loading)
    val screenState: StateFlow<QuizScreenState> = _screenState.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    private val _selectedAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val selectedAnswers: StateFlow<Map<Int, Int>> = _selectedAnswers.asStateFlow()

    val isQuizFinished = MutableStateFlow(false)

    init {
        generateQuizList()
    }

    private fun generateQuizList() {
        viewModelScope.launch {
            val questions = useCases.generateQuizListUseCase().first()
            _screenState.value = QuizScreenState.Success(questions)
        }
    }

    fun onEvent(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.SelectedAnswers -> {
                _selectedAnswers.value = _selectedAnswers.value.toMutableMap().apply {
                    put(event.questionIndex, event.answerIndex)
                }

                Log.d(
                    "DriverTheory",
                    "${event.questionIndex} question index, ${event.answerIndex} answer index"
                )
            }

            is ScreenEvent.NextQuestion -> {
                if (_currentIndex.value < (_screenState.value as QuizScreenState.Success).state.size - 1) _currentIndex.value++
                else isQuizFinished.value = true
                Log.d("DriverTheory", "${_currentIndex.value} current index,")
                showSelectedAnswers()
            }

            is ScreenEvent.PreviousQuestion -> {
                if (_currentIndex.value > 0) _currentIndex.value--
                isQuizFinished.value = false
                Log.d("DriverTheory", "${_currentIndex.value} current index,")
                showSelectedAnswers()
            }

            is ScreenEvent.FinishQuiz -> {

            }

            is ScreenEvent.StartTimer -> {
                Log.d("DriverTheory", "Timer Was Started.")
            }

        }
    }

    private fun showSelectedAnswers() {
        _selectedAnswers.value.forEach { answer ->
            Log.d("DriverTheory", "Answer id: ${answer.key} answer index: ${answer.value}")
        }
    }

    sealed class QuizScreenState() {
        data object Loading : QuizScreenState()
        data class Success(val state: List<QuizModel>) : QuizScreenState()
        data class Error(val error: String) : QuizScreenState()
    }

    sealed class ScreenEvent {
        data class SelectedAnswers(val questionIndex: Int, val answerIndex: Int) : ScreenEvent()
        object NextQuestion : ScreenEvent()
        object PreviousQuestion : ScreenEvent()
        object FinishQuiz : ScreenEvent()
        object StartTimer : ScreenEvent()
    }
}