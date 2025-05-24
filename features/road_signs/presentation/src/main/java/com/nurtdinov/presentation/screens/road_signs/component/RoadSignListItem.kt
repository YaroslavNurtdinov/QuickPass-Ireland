package com.nurtdinov.presentation.screens.road_signs.component

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("DiscouragedApi")
@Composable
fun RoadSignListItem(
    id: Int,
    question: String,
    correctAnswer: String,
    imageName: String,
    onClick: () -> Unit,
    favorites: Boolean,
) {

    val context = LocalContext.current
    val resId = remember(imageName) {
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    Card(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = if (favorites) {
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )

        } else {
            BorderStroke(width = 0.dp, color = MaterialTheme.colorScheme.onSecondary)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = ParagraphStyle(
                                textIndent = TextIndent(
                                    firstLine = 0.sp,
                                )
                            )
                        ) {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("${id + 1}. ")
                            }
                            append(question)
                        }
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp, bottom = 2.dp, end = 8.dp),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    thickness = 2.dp
                )
                Text(text = correctAnswer, fontSize = 18.sp)
            }
            if (resId != 0) {
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }
}
