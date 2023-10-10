package com.kholiodev.esportsbuzz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.kholiodev.esportsbuzz.ui.AppState
import com.kholiodev.matches.navigation.matchesRoute
import com.kholiodev.matches.navigation.matchesScreen

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = matchesRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        matchesScreen(onTopicClick = {})
    }
}