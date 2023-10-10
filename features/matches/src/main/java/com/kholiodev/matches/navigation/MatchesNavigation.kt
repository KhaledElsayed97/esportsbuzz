package com.kholiodev.matches.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kholiodev.matches.MatchesRoute

const val matchesRoute = "matches_route"
fun NavController.navigateToMatches(navOptions: NavOptions? = null) {
    this.navigate(matchesRoute, navOptions)
}

fun NavGraphBuilder.matchesScreen(onTopicClick: (String) -> Unit) {
    composable(
        route = matchesRoute,
    ) {
        MatchesRoute(onTopicClick)
    }
}