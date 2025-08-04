package com.kholiodev.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kholiodev.news.NewsRoute

const val newsRoute = "news_route"
fun NavController.navigateToNews(navOptions: NavOptions? = null) {
    this.navigate(newsRoute, navOptions)
}

fun NavGraphBuilder.newsScreen(onNewsClick: (String) -> Unit) {
    composable(
        route = newsRoute,
    ) {
        NewsRoute(onNewsClick)
    }
} 