package com.nurtdinov.quickpassireland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.nurtdinov.presentation.route.DriverGraph
import com.nurtdinov.presentation.DriverHomeScreen
import com.nurtdinov.presentation.HomeScreen
import com.nurtdinov.presentation.route.TheoryGraph
import com.nurtdinov.presentation.components.HomeTabs
import com.nurtdinov.presentation.route.ListGraph
import com.nurtdinov.presentation.route.ProfileGraph
import com.nurtdinov.quickpassireland.navigation.routeClass
import com.nurtdinov.quickpassireland.ui.components.AppNavigationBar
import com.nurtdinov.quickpassireland.components.AppTollBar
import com.nurtdinov.quickpassireland.components.NavigateUpAction
import com.nurtdinov.quickpassireland.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                NavApp()
            }
        }
    }
}

@Composable
fun NavApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val titleRes = when (currentBackStackEntry?.routeClass()) {
        TheoryGraph.TheoryHomeRoute::class -> R.string.hello
        TheoryGraph.QuizRoute::class -> R.string.you_have_40_minutes
        TheoryGraph.HistoryRoute::class -> R.string.progress_history
        TheoryGraph.SettingsRoute::class -> R.string.settings
        else -> R.string.app_name
    }
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        topBar = {
            AppTollBar(
                titleRes = titleRes,
                navigateUpAction = if (navController.previousBackStackEntry == null) {
                    NavigateUpAction.Profile(
                        onClick = { navController.navigate(ProfileGraph.ProfileRoute) }
                    )
                } else {
                    NavigateUpAction.Back(
                        onClick = { navController.navigateUp() }
                    )
                }, onActionClick = {
                    navController.navigate(TheoryGraph.SettingsRoute)
                }
            )
        },
        bottomBar = {
            AppNavigationBar(
                navController = navController,
                tabs = HomeTabs
            )
        }
    ) { paddingValue ->

        NavHost(
            navController = navController,
            startDestination = TheoryGraph,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            navigation<ProfileGraph>(startDestination = ProfileGraph.SignInRoute) {
                composable<ProfileGraph.SignInRoute> {}
                composable<ProfileGraph.SignUpRoute> {}
                composable<ProfileGraph.ProfileRoute> { ProfileScreen() }
            }
            navigation<TheoryGraph>(startDestination = TheoryGraph.TheoryHomeRoute) {
                composable<TheoryGraph.TheoryHomeRoute> { HomeScreen(navController) }
                composable<TheoryGraph.QuizRoute> {}
                composable<TheoryGraph.HistoryRoute> {}
                composable<TheoryGraph.SettingsRoute> { SettingsScreen() }
            }

            navigation<DriverGraph>(startDestination = DriverGraph.DriverHomeRoute) {
                composable<DriverGraph.DriverHomeRoute> { DriverHomeScreen() }
            }
            navigation<ListGraph>(startDestination = ListGraph.QuestionsListRoute) {
                composable<ListGraph.QuestionsListRoute> { }
                composable<ListGraph.SignsListRoute> { }
            }
        }
    }
}


@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Settings Screen", fontSize = 32.sp)
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Profile Screen", fontSize = 32.sp)
    }
}

