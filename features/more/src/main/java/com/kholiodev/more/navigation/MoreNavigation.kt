package com.kholiodev.more.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kholiodev.more.MoreRoute

const val moreRoute = "more_route"

fun NavController.navigateToMore(navOptions: NavOptions? = null) {
    this.navigate(moreRoute, navOptions)
}

fun NavGraphBuilder.moreScreen(
    onMenuItemClick: (String) -> Unit
) {
    composable(
        route = moreRoute,
    ) {
        MoreRoute(onMenuItemClick = onMenuItemClick)
    }
} 