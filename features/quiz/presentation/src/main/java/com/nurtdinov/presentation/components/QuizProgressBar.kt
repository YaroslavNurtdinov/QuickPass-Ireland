package com.nurtdinov.presentation.components

import android.R.attr.contentDescription
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuizProgressBarMultiRow(
    currentIndex: Int,
    total: Int,
    itemsPerRow: Int = 10, // Кількість кружечків в одному ряду
    modifier: Modifier = Modifier
) {
    val baseSize = 8.dp

    val rows = (0 until total).chunked(itemsPerRow)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        rows.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically
            ) {
                rowItems.forEach { index ->
                    val animatedSize by animateDpAsState(
                        targetValue = if (index == currentIndex) baseSize * 1.9f else baseSize,
                        label = "circleSize"
                    )
                    val animatedColor by animateColorAsState(
                        targetValue = when {
                            index < currentIndex -> Color.Gray
                            index == currentIndex -> Color(0xFF1976D2)
                            else -> Color.LightGray.copy(alpha = 0.5f)
                        },
                        label = "circleColor"
                    )

                    Box(
                        modifier = Modifier
                            .size(animatedSize)
                            .clip(CircleShape)
                            .background(animatedColor)
                    )
                }
            }
        }
    }
}

