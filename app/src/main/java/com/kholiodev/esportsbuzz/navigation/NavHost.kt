package com.kholiodev.esportsbuzz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.kholiodev.esportsbuzz.ui.AppState
import com.kholiodev.matches.navigation.matchesScreen
import com.kholiodev.matches.navigation.matchDetailsScreen
import com.kholiodev.matches.navigation.standingsScreen
import com.kholiodev.matches.navigation.bracketsScreen
import com.kholiodev.matches.navigation.navigateToMatches
import com.kholiodev.matches.navigation.navigateToMatchDetails
import com.kholiodev.matches.navigation.navigateToStandings
import com.kholiodev.matches.navigation.navigateToBrackets
import com.kholiodev.onboarding.navigation.onboardingRoute
import com.kholiodev.onboarding.navigation.onboardingScreen
import com.kholiodev.more.navigation.moreScreen
import com.kholiodev.news.navigation.newsScreen
import com.kholiodev.following.navigation.followingScreen
import com.kholiodev.following.navigation.navigateToGameDetails

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = onboardingRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        matchesScreen(onMatchClick = { matchId ->
            navController.navigateToMatchDetails(matchId)
        })
        matchDetailsScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onViewStandings = { matchId ->
                navController.navigateToStandings(matchId)
            },
            onViewBrackets = { matchId ->
                navController.navigateToBrackets(matchId)
            }
        )
        standingsScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
        bracketsScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
        newsScreen(onNewsClick = {})
        followingScreen(
            onItemClick = { gameId ->
                navController.navigateToGameDetails(gameId)
            },
            onBackClick = {
                navController.popBackStack()
            }
        )
        moreScreen(onMenuItemClick = { menuItemId ->
            // Handle menu item clicks
            when (menuItemId) {
                "notifications" -> {
                    // Handle notifications
                }
                "terms_and_conditions" -> {
                    // Handle terms and conditions
                }
                "support" -> {
                    // Handle support
                }
            }
        })
        onboardingScreen(onGetStarted = {
            navController.navigateToMatches()
        })
    }
}