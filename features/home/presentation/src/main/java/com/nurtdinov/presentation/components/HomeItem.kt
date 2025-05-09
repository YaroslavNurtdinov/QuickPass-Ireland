package com.nurtdinov.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nurtdinov.presentation.R
import com.nurtdinov.presentation.route.ListGraph
import com.nurtdinov.presentation.route.TheoryGraph
import kotlinx.collections.immutable.persistentListOf


data class HomeItemModel(
    val image: Int,
    val text: String,
    val route: Any,
)

val homeItems = persistentListOf<HomeItemModel>(
    HomeItemModel(
        image = R.drawable.sign,
        text = "Road sign",
        route = ListGraph.SignsListRoute
    ),
    HomeItemModel(
        image = R.drawable.history,
        text = "History",
        route = TheoryGraph.HistoryRoute,
    ),
    HomeItemModel(
        image = R.drawable.list,
        text = "Questions",
        route = ListGraph.SignsListRoute,
    ),
    HomeItemModel(
        image = R.drawable.settings_image,
        text = "Settings",
        route = TheoryGraph.SettingsRoute,
    )
)

@Composable
fun HomeItem(
    item: HomeItemModel,
    onClick: (Any) -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .clickable(onClick = { onClick(item.route) }),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Картинка на фоні
            AsyncImage(
                model = item.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Темний напівпрозорий фон під текст
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.4f))
                    .padding(4.dp)
            ) {
                Text(
                    text = item.text,
                    fontSize = 21.sp,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeItemPreview() {
    HomeItem(homeItems[0], onClick = {})
}
