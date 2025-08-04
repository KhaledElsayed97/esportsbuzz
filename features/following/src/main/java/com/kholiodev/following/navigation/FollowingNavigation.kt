package com.kholiodev.following.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kholiodev.following.FollowingRoute
import com.kholiodev.following.GameDetailsRoute

const val followingRoute = "following_route"
const val gameDetailsRoute = "game_details_route/{gameId}"

fun NavController.navigateToFollowing(navOptions: NavOptions? = null) {
    this.navigate(followingRoute, navOptions)
}

fun NavController.navigateToGameDetails(gameId: String, navOptions: NavOptions? = null) {
    this.navigate("game_details_route/$gameId", navOptions)
}

fun NavGraphBuilder.followingScreen(
    onItemClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    composable(
        route = followingRoute,
    ) {
        FollowingRoute(onItemClick)
    }
    
    composable(
        route = gameDetailsRoute,
    ) { backStackEntry ->
        val gameId = backStackEntry.arguments?.getString("gameId") ?: ""
        GameDetailsRoute(
            gameId = gameId,
            onBackClick = onBackClick,
            onItemClick = onItemClick
        )
    }
} 