package com.nurtdinov.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nurtdinov.presentation.QuizViewModel

@Composable
fun QuizButtonsRow(
    onEvent: (QuizViewModel.ScreenEvent) -> Unit,
    isQuizFinished: Boolean,
    modifier: Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .padding(bottom = 12.dp)
            // .navigationBarsPadding()
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onEvent(QuizViewModel.ScreenEvent.PreviousQuestion) }) {
                Text("Back", fontSize = 24.sp)
            }

            AnimatedVisibility(
                visible = isQuizFinished,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                FloatingActionButton(
                    onClick = { onEvent(QuizViewModel.ScreenEvent.FinishQuiz) }
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(imageVector = Icons.Default.Done, contentDescription = null)
                        Text(
                            text = "Finish",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize
                        )
                    }
                }
            }

            Button(onClick = { onEvent(QuizViewModel.ScreenEvent.NextQuestion) }) {
                Text("Next", fontSize = 24.sp)
            }
        }

    }
}