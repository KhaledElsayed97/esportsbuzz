package com.kholiodev.matches.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kholiodev.matches.MatchesRoute
import com.kholiodev.matches.details.MatchDetailsRoute
import com.kholiodev.matches.standings.StandingsRoute
import com.kholiodev.matches.brackets.BracketsRoute

const val matchesRoute = "matches_route"
const val matchDetailsRoute = "match_details_route/{matchId}"
const val matchIdArg = "matchId"
const val standingsRoute = "standings_route/{tournamentId}"
const val bracketsRoute = "brackets_route/{tournamentId}"
const val tournamentIdArg = "tournamentId"

fun NavController.navigateToMatches(navOptions: NavOptions? = null) {
    this.navigate(matchesRoute, navOptions)
}

fun NavController.navigateToMatchDetails(matchId: String, navOptions: NavOptions? = null) {
    this.navigate("match_details_route/$matchId", navOptions)
}

fun NavController.navigateToStandings(tournamentId: String, navOptions: NavOptions? = null) {
    this.navigate("standings_route/$tournamentId", navOptions)
}

fun NavController.navigateToBrackets(tournamentId: String, navOptions: NavOptions? = null) {
    this.navigate("brackets_route/$tournamentId", navOptions)
}

fun NavGraphBuilder.matchesScreen(onMatchClick: (String) -> Unit) {
    composable(
        route = matchesRoute,
    ) {
        MatchesRoute(onMatchClick)
    }
}

fun NavGraphBuilder.matchDetailsScreen(
    onBackClick: () -> Unit,
    onViewStandings: (String) -> Unit,
    onViewBrackets: (String) -> Unit
) {
    composable(
        route = matchDetailsRoute,
        arguments = listOf(
            navArgument(matchIdArg) { type = androidx.navigation.NavType.StringType }
        )
    ) { backStackEntry ->
        val matchId = backStackEntry.arguments?.getString(matchIdArg) ?: ""
        MatchDetailsRoute(
            matchId = matchId,
            onBackClick = onBackClick,
            onViewStandings = onViewStandings,
            onViewBrackets = onViewBrackets
        )
    }
}

fun NavGraphBuilder.standingsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = standingsRoute,
        arguments = listOf(
            navArgument(tournamentIdArg) { type = androidx.navigation.NavType.StringType }
        )
    ) { backStackEntry ->
        val tournamentId = backStackEntry.arguments?.getString(tournamentIdArg) ?: ""
        StandingsRoute(
            tournamentId = tournamentId,
            onBackClick = onBackClick
        )
    }
}

fun NavGraphBuilder.bracketsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = bracketsRoute,
        arguments = listOf(
            navArgument(tournamentIdArg) { type = androidx.navigation.NavType.StringType }
        )
    ) { backStackEntry ->
        val tournamentId = backStackEntry.arguments?.getString(tournamentIdArg) ?: ""
        BracketsRoute(
            tournamentId = tournamentId,
            onBackClick = onBackClick
        )
    }
}