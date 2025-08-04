package com.kholiodev.esportsbuzz.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.kholiodev.core.ui.components.AppBackground
import com.kholiodev.core.ui.components.AppGradientBackground
import com.kholiodev.core.ui.components.EbuzzNavigationBar
import com.kholiodev.core.ui.components.EbuzzNavigationBarItem
import com.kholiodev.core.ui.theme.AppTheme
import com.kholiodev.core.ui.theme.GradientColors
import com.kholiodev.core.ui.theme.LocalGradientColors
import com.kholiodev.esportsbuzz.navigation.AppNavHost
import com.kholiodev.esportsbuzz.navigation.TopLevelDestination
import com.kholiodev.following.navigation.followingRoute
import com.kholiodev.matches.navigation.matchesRoute
import com.kholiodev.more.navigation.moreRoute
import com.kholiodev.news.navigation.newsRoute
import com.kholiodev.onboarding.navigation.onboardingRoute
import kotlinx.coroutines.launch
import com.kholiodev.matches.R as matchesR

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun App(
    appState: AppState = rememberAppState(),
) {
    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == TopLevelDestination.MATCHES
    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }
    val shouldShowBottomBar = appState.currentDestination?.route?.let { route ->
        route != onboardingRoute && !route.startsWith("match_details_route")
    } ?: true
    var liveFilterChecked by rememberSaveable { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    
    AppTheme(gamingTheme = false) {
        AppBackground {
            AppGradientBackground(
                gradientColors = if (shouldShowGradientBackground) {
                    LocalGradientColors.current
                } else {
                    GradientColors()
                },
            ) {
            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = {
                    EbuzzBottomAppBar(
                        destinations = appState.topLevelDestinations,
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        shouldShowBotBar = shouldShowBottomBar
                    )
                },
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),
                ) {
                    Column(Modifier.fillMaxSize()) {
                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
                            com.kholiodev.core.ui.components.EbuzzTopAppBar(
                                titleRes = matchesR.string.app_name, // Use your app name string resource
                                showNavigationIcon = false,
                                onNavigationClick = null,
                                showLiveFilter = destination == TopLevelDestination.MATCHES,
                                liveFilterChecked = liveFilterChecked,
                                onLiveFilterChange = { checked ->
                                    liveFilterChecked = checked
                                    // Show snackbar for demo
                                    kotlinx.coroutines.GlobalScope.launch {
                                        snackbarHostState.showSnackbar(
                                            if (checked) "Live matches only" else "All matches"
                                        )
                                    }
                                },
                                onSearchClick = {
                                    kotlinx.coroutines.GlobalScope.launch {
                                        snackbarHostState.showSnackbar("Search clicked")
                                    }
                                },
                                onCalendarClick = {
                                    kotlinx.coroutines.GlobalScope.launch {
                                        snackbarHostState.showSnackbar("Calendar clicked")
                                    }
                                },
                            )
                        }
                        AppNavHost(appState = appState)
                    }
                }
            }
        }
    }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}

@Composable
fun EbuzzBottomAppBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    shouldShowBotBar: Boolean = false,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = shouldShowBotBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        EbuzzNavigationBar(
            modifier = modifier,
        ) {
            destinations.forEach { destination ->
                val selected = when (destination) {
                    TopLevelDestination.MATCHES -> currentDestination?.route == matchesRoute
                    TopLevelDestination.NEWS -> currentDestination?.route == newsRoute
                    TopLevelDestination.FOLLOWING -> currentDestination?.route == followingRoute
                    TopLevelDestination.MORE -> currentDestination?.route == moreRoute
                }
                EbuzzNavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(destination.unselectedIcon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(destination.selectedIcon ),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) },
                )
            }
        }
    }
}