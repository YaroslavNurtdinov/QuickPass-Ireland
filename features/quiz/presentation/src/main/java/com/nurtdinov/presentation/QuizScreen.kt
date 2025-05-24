package com.nurtdinov.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurtdinov.presentation.components.QuizButtonsRow
import com.nurtdinov.presentation.components.QuizCaseContent
import com.nurtdinov.presentation.components.QuizProgressBarMultiRow


@Composable
fun QuizScreen() {
    val viewModel: QuizViewModel = hiltViewModel()
    val screenState = viewModel.screenState.collectAsState()
    val currentIndex = viewModel.currentIndex.collectAsState()
    val selectedAnswers = viewModel.selectedAnswers.collectAsState()
    val isQuizFinished = viewModel.isQuizFinished.collectAsState()
    QuizContent(
        getScreenState = { screenState.value },
        currentIndex = currentIndex.value,
        selectedAnswers = selectedAnswers.value,
        isQuizFinished = isQuizFinished.value,
        onEvent = viewModel::onEvent,
    )
}


@Composable
fun QuizContent(
    getScreenState: () -> QuizViewModel.QuizScreenState,
    currentIndex: Int,
    selectedAnswers: Map<Int, Int>,
    isQuizFinished: Boolean,
    onEvent: (QuizViewModel.ScreenEvent) -> Unit,
) {
    var swipeHandled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            QuizProgressBarMultiRow(
                currentIndex = currentIndex,
                total = 40,
                itemsPerRow = 20
            )
        },
        bottomBar = {
            QuizButtonsRow(
                onEvent = onEvent,
                isQuizFinished = isQuizFinished,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )
        }) { paddingValue ->
        when (val getScreenState = getScreenState()) {
            QuizViewModel.QuizScreenState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            is QuizViewModel.QuizScreenState.Success -> {
                AnimatedContent(
                    targetState = currentIndex,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (slideInHorizontally { width -> width } + fadeIn()).togetherWith(
                                slideOutHorizontally { width -> -width } + fadeOut())
                        } else {
                            (slideInHorizontally { width -> -width } + fadeIn()).togetherWith(
                                slideOutHorizontally { width -> width } + fadeOut())
                        }
                    },
                    label = "QuizPageTransition"
                ) { index ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValue)
                            .navigationBarsPadding()
                            .pointerInput(Unit) {
                                detectHorizontalDragGestures(
                                    onDragStart = {
                                        swipeHandled = false
                                    },
                                    onHorizontalDrag = { _, dragAmount ->
                                        if (!swipeHandled) {
                                            when {
                                                dragAmount > 50 -> {
                                                    swipeHandled = true
                                                    onEvent(QuizViewModel.ScreenEvent.PreviousQuestion)
                                                }

                                                dragAmount < -50 -> {
                                                    swipeHandled = true
                                                    onEvent(QuizViewModel.ScreenEvent.NextQuestion)
                                                }
                                            }
                                        }
                                    }
                                )
                            }
                    ) {

                        QuizCaseContent(
                            stateQuestion = getScreenState.state[index],
                            onEvent = onEvent,
                            selectedAnswer = selectedAnswers,
                            modifier = Modifier.weight(1F),
                            index = index + 1
                        )

                    }
                }

            }

            is QuizViewModel.QuizScreenState.Error -> {
                Text(text = "Update your application.")
            }
        }
    }
}
