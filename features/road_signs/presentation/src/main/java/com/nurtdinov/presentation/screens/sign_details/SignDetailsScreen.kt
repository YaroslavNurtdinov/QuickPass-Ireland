package com.nurtdinov.presentation.screens.sign_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nurtdinov.domain.model.RoadSignModel
import com.nurtdinov.resources.R

@Composable
fun SignDetailsScreen(
    id: Int,
    onNavBackClick: () -> Unit
) {
    val viewModel = hiltViewModel<SignDetailsViewModel, SignDetailsViewModel.Factory> { factory ->
        factory.create(id)
    }
    val state = viewModel.state
    SignDetailsContent(
        state = state.value,
        onNavBackClick = onNavBackClick,
        onFavoriteClick = { viewModel.uiEvent(SignDetailsViewModel.UiEvent.OnFavoritesClick(it)) })
}

@Composable
fun SignDetailsContent(
    state: SignDetailsViewModel.SignDetailScreenState,
    onNavBackClick: () -> Unit,
    onFavoriteClick: (RoadSignModel) -> Unit,
) {
    val context = LocalContext.current
    when (state) {
        is SignDetailsViewModel.SignDetailScreenState.Loading -> {
            CircularProgressIndicator()
        }

        is SignDetailsViewModel.SignDetailScreenState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val resId = remember(state) {
                    context.resources.getIdentifier(
                        state.sign.image,
                        "drawable",
                        context.packageName
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onNavBackClick() },
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null, modifier = Modifier.size(32.dp)
                        )
                    }

                    IconButton(
                        onClick = { onFavoriteClick(state.sign) },
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(if (state.sign.favorites) R.drawable.favorites_true_star_24 else R.drawable.favorites_false_star_border_24),
                            contentDescription = null, modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }


                Image(
                    painter = painterResource(id = resId),
                    contentDescription = state.sign.correctAnswer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.sign.correctAnswer,
                    modifier = Modifier
                        .weight(0.5F)
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 32.sp,
                        lineHeight = 32.sp
                    )
                )
                Text(
                    text = state.sign.explanation,
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 28.sp,
                        lineHeight = 28.sp
                    )
                )
            }
        }

        is SignDetailsViewModel.SignDetailScreenState.Error -> {
            Text(text = state.message)
        }
    }

}