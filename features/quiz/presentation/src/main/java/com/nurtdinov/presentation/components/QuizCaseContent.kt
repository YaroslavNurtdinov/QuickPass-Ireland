package com.nurtdinov.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nurtdinov.domain.model.QuizModel
import com.nurtdinov.presentation.QuizViewModel
import com.nurtdinov.resources.R


@Composable
fun QuizCaseContent(
    stateQuestion: QuizModel?,
    onEvent: (QuizViewModel.ScreenEvent) -> Unit,
    selectedAnswer: Map<Int, Int>,
    index: Int,
    modifier: Modifier
) {
    val selectedOption = rememberSaveable { mutableIntStateOf(-1) }
    val context = LocalContext.current
    val resId = remember(stateQuestion?.image) {
        context.resources.getIdentifier(stateQuestion?.image, "drawable", context.packageName)
    }

    LaunchedEffect(stateQuestion?.id) {
        selectedOption.intValue = selectedAnswer[stateQuestion?.id] ?: -1
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding()
            .padding(start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        if (resId != 0) {
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)

            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ireland),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)

            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "${index}. ${stateQuestion?.question}",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        stateQuestion?.answers?.forEachIndexed { index, answer ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clickable {
                        selectedOption.intValue = index
                        onEvent(QuizViewModel.ScreenEvent.SelectedAnswers(stateQuestion.id, index))
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedOption.intValue == index,
                    onClick = {
                        selectedOption.intValue = index
                        onEvent(QuizViewModel.ScreenEvent.SelectedAnswers(stateQuestion.id, index))
                    }, colors = RadioButtonColors(
                        selectedColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledUnselectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        disabledSelectedColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                )
                Text(
                    text = answer,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Normal

                )
            }
        }
    }
}


