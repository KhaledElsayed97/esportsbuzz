package com.kholiodev.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kholiodev.core.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onGetStarted: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    
    val onboardingPagesList = listOf(
        OnboardingPage(
            R.raw.ill_gamers,
            "Follow Your Favorite Gamer",
            "Keep track of what your favorite player is doing"
        ),
        OnboardingPage(
            R.raw.ill_schedule,
            "Never Miss A Match",
            "Matches timings and dates all in one place"
        ),
        OnboardingPage(
            R.raw.ill_scores,
            "Know Every Result",
            "Tournaments results and standings just one click away"
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        onboardingPagesList.size
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { pagerIndex ->
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(
                    onboardingPagesList[pagerIndex].resId
                )
            )
            composition?.let {
                OnboardingPager(
                    onboardingPage = onboardingPagesList[pagerIndex],
                    it
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Pagination indicators
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    onBoardingPageList = onboardingPagesList
                )
            
            // Navigation buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Skip button (left)
                TextButton(
                    onClick = onGetStarted,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Skip",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                // Next/Finish button (right)
                AnimatedVisibility(
                    visible = pagerState.currentPage != 2,
                    modifier = Modifier
                ) {
                    Button(
                        onClick = {
                            // Go to next page
                            if (pagerState.currentPage < onboardingPagesList.size - 1) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Next",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
                
                // Get Started button (right) - shown on last page
                AnimatedVisibility(
                    visible = pagerState.currentPage == 2,
                    modifier = Modifier
                ) {
                    Button(
                        onClick = onGetStarted,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Get Started",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicator(
    pagerState: PagerState,
    onBoardingPageList: List<OnboardingPage>
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 24.dp)
    ) {
        repeat(onBoardingPageList.size) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(12.dp)
            )
        }
    }
}

@Composable
fun OnboardingPager(onboardingPage: OnboardingPage, lottieComposition: LottieComposition) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Logo section
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = "ESPORTS\nBUZZ.",
            fontSize = 48.sp,
            lineHeight = 36.sp,
            fontFamily = FontFamily(Font(com.kholiodev.core.R.font.museomoderno_bold)),
            textAlign = TextAlign.Center
        )
        
        // Lottie animation section
        LottieAnimation(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
        )
        
        // Content section
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingPage.title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = onboardingPage.description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}



@Preview
@Composable
fun OnboardingPagePreview() {
    AppTheme {
    }
}