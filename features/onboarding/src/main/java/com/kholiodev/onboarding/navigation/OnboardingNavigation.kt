package com.kholiodev.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kholiodev.onboarding.OnboardingScreen

const val onboardingRoute = "onboarding_route"
fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
    this.navigate(onboardingRoute, navOptions)
}

fun NavGraphBuilder.onboardingScreen(onGetStarted: () -> Unit) {
    composable(
        route = onboardingRoute,
    ) {
        OnboardingScreen(onGetStarted = onGetStarted)
    }
}