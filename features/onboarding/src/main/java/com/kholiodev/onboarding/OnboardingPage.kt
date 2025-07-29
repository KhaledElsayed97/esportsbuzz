package com.kholiodev.onboarding

import androidx.annotation.RawRes

data class OnboardingPage(
    @RawRes var resId: Int,
    var title: String,
    var description:String
)
