package com.kholiodev.esportsbuzz.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.kholiodev.esportsbuzz.navigation.TopLevelDestination
import com.kholiodev.matches.navigation.matchesRoute
import com.kholiodev.matches.navigation.navigateToMatches
import com.kholiodev.news.navigation.newsRoute
import com.kholiodev.news.navigation.navigateToNews
import com.kholiodev.following.navigation.followingRoute
import com.kholiodev.following.navigation.navigateToFollowing
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): AppState {

    return remember(coroutineScope,navController){
        AppState(navController = navController, coroutineScope = coroutineScope)
    }
}

@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            matchesRoute -> TopLevelDestination.MATCHES
            newsRoute -> TopLevelDestination.NEWS
            followingRoute -> TopLevelDestination.FOLLOWING
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.MATCHES -> navController.navigateToMatches(topLevelNavOptions)
                TopLevelDestination.NEWS -> navController.navigateToNews(topLevelNavOptions)
                TopLevelDestination.FOLLOWING -> navController.navigateToFollowing(topLevelNavOptions)
            }
    }

    fun navigateToSearch() {
    }
}