package com.nurtdinov.presentation.components

import com.nurtdinov.presentation.route.DriverGraph
import com.nurtdinov.presentation.R
import com.nurtdinov.presentation.route.TheoryGraph
import kotlinx.collections.immutable.persistentListOf

data class HomeTab(
    val route: Any,
    val painter: Int,
    val label: Int
)


val HomeTabs = persistentListOf(
    HomeTab(
        route = TheoryGraph,
        painter = R.drawable.school,
        label = R.string.mock_theory_test
    ),
    HomeTab(
        route = DriverGraph,
        painter = R.drawable.car,
        label = R.string.mock_driver_theory_test
    )
)

