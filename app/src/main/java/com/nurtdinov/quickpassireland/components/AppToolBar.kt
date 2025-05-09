package com.nurtdinov.quickpassireland.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.nurtdinov.presentation.R
import com.nurtdinov.quickpassireland.R.*

sealed class NavigateUpAction {
    data class Profile(
        val onClick: () -> Unit
    ) : NavigateUpAction()

    data class Back(
        val onClick: () -> Unit,
    ) : NavigateUpAction()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTollBar(
    @StringRes titleRes: Int,
    navigateUpAction: NavigateUpAction,
    onActionClick: () -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(titleRes),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        navigationIcon = {
            if (navigateUpAction is NavigateUpAction.Back) {
                IconButton(onClick = {
                    navigateUpAction.onClick()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            } else if (navigateUpAction is NavigateUpAction.Profile) {
                IconButton(
                    onClick = {
                        navigateUpAction.onClick()
                    },
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary, shape = CircleShape
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),

                        )
                }
            }
        }, actions = {

            Row(modifier = Modifier.padding(end = 12.dp)) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(28.dp)
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        offset = DpOffset(
                            x = 67.dp,
                            y = 5.dp
                        )
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(string.share))
                            },
                            onClick = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = null
                                )
                            }

                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(string.info))
                            },
                            onClick = {},
                            leadingIcon = {
                                Icon(imageVector = Icons.Outlined.Info, contentDescription = null)
                            }

                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(string.report))
                            },
                            onClick = {},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = null
                                )
                            }

                        )
                    }
                }
                IconButton(onClick = {
                    onActionClick()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.settings_image),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = Modifier
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                clip = true
            )

            .background(color = MaterialTheme.colorScheme.background)
    )
}