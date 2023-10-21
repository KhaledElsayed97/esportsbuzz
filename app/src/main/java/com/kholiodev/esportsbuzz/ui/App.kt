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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kholiodev.core.ui.components.AppBackground
import com.kholiodev.core.ui.components.AppGradientBackground
import com.kholiodev.core.ui.components.EbuzzNavigationBar
import com.kholiodev.core.ui.components.EbuzzNavigationBarItem
import com.kholiodev.core.ui.components.EbuzzTopAppBar
import com.kholiodev.core.ui.theme.GradientColors
import com.kholiodev.core.ui.theme.LocalGradientColors
import com.kholiodev.esportsbuzz.navigation.AppNavHost
import com.kholiodev.esportsbuzz.navigation.TopLevelDestination
import com.kholiodev.onboarding.navigation.onboardingRoute
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
    val shouldShowBottomBar = appState.currentDestination?.route != onboardingRoute

    AppBackground {
        AppGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

//            if (showSettingsDialog) {
//                SettingsDialog(
//                    onDismiss = { showSettingsDialog = false },
//                )
//            }

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
                        // Show the top app bar on top level destinations.
                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
                            com.kholiodev.core.ui.components.EbuzzTopAppBar(
                                titleRes = destination.titleId,
                                navigationIcon = Icons.Default.Search,
                                navigationIconContentDescription = stringResource(
                                    id = matchesR.string.matches,
                                ),
                                actionIcon = Icons.Default.Settings,
                                actionIconContentDescription = stringResource(
                                    id = matchesR.string.matches,
                                ),
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color.Transparent,
                                ),
                                onActionClick = { showSettingsDialog = true },
                                onNavigationClick = { appState.navigateToSearch() },
                            )
                        }

                        AppNavHost(appState = appState)
                    }

                    // TODO: We may want to add padding or spacer when the snackbar is shown so that
                    //  content doesn't display behind it.
                }
            }
        }
    }
}

@Composable
fun EbuzzBottomAppBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    shouldShowBotBar:Boolean = false,
    modifier: Modifier = Modifier,

    ) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    AnimatedVisibility(
        visible = shouldShowBotBar,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })) {
        EbuzzNavigationBar(
            modifier = modifier,
        ) {
            destinations.forEach { destination ->
                val selected =
                    currentDestination?.route == currentBackStackEntry?.destination?.route
                EbuzzNavigationBarItem(
                    selected = selected,
                    onClick = { onNavigateToDestination(destination) },
                    icon = {
                        Icon(
                            imageVector = destination.unselectedIcon,
                            contentDescription = null,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = destination.selectedIcon,
                            contentDescription = null,
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) },
                )
            }
        }
    }
}