package com.nurtdinov.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeOptionItem(
    item: HomeOption,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.onTertiaryContainer),
        onClick = {
            onClick()
        }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(corner = CornerSize(size = 12.dp)),
                    clip = true
                )
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    border = BorderStroke(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable(
                    onClick = {
                        onClick()
                    }
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.text,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(start = 24.dp, top = 8.dp, bottom = 8.dp)
            )
            Icon(
                painter = painterResource(id = item.painter),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .size(32.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        if (item.description) {
            Column {
                Text(
                    text = "Test time 40 minutes." +
                            "\nAll questions and illustrations from Official resources. ",
                    modifier = Modifier.padding(start = 24.dp),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

