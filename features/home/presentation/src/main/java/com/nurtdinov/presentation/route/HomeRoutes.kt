package com.nurtdinov.presentation.route

import androidx.navigation.NavDestination
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


@Serializable
data object TheoryGraph {
    @Serializable
    data object TheoryHomeRoute

    @Serializable
    data object QuizRoute

    @Serializable
    data object FavoritesRoute

    @Serializable
    data object HistoryRoute

    @Serializable
    data object SettingsRoute
}

@Serializable
data object ListGraph {
    @Serializable
    data object QuestionsListRoute

    @Serializable
    data object SignsListRoute

    @Serializable
    data class SignsDetailsRoute(
        val id: Int,
    )
}

@Serializable
data object DriverGraph {
    @Serializable
    data object DriverHomeRoute
}

@Serializable
data object ProfileGraph {
    @Serializable
    data object ProfileRoute

    @Serializable
    data object SignInRoute

    @Serializable
    data object SignUpRoute
}


fun NavDestination?.routeClass(): KClass<*>? {
    return this?.route
        ?.split("/")
        ?.first()
        ?.let { classname ->
            generateSequence(classname, ::replaceLastDotByDollar)
                .mapNotNull(::tryParseClass)
                .firstOrNull()
        }
}

private fun tryParseClass(className: String): KClass<*>? {
    return runCatching { Class.forName(className).kotlin }.getOrNull()
}

private fun replaceLastDotByDollar(input: String): String? {
    val index = input.lastIndexOf('.')
    return if (index != -1) {
        String(input.toCharArray().apply { set(index, '$') })
    } else {
        null
    }
}
