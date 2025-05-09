package com.nurtdinov.presentation.components

import com.nurtdinov.presentation.R
import com.nurtdinov.presentation.route.ListGraph
import com.nurtdinov.presentation.route.TheoryGraph
import kotlinx.collections.immutable.persistentListOf

data class HomeOption(
    val text: String,
    val painter: Int,
    val description: Boolean,
    val route: Any,
)

val HomeOptions = persistentListOf(

    HomeOption(
        text = "Test History",
        painter = R.drawable.history,
        description = false,
        route = TheoryGraph.HistoryRoute,
    ),
    HomeOption(
        text = "Road signs",
        painter = R.drawable.road_sign,
        description = false,
        route = TheoryGraph.SettingsRoute,
    ),
    HomeOption(
        text = "Questions",
        painter = R.drawable.list,
        description = false,
        route = ListGraph.QuestionsListRoute,
    ),
    HomeOption(
        text = "Launch Mock Test",
        painter = R.drawable.rocket_launch,
        description = true,
        route = TheoryGraph.QuizRoute,
    ),
)



