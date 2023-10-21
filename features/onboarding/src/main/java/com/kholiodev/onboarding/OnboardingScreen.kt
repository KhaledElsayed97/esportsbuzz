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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kholiodev.core.ui.theme.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun OnboardingScreen(
    modifier: Modifier = Modifier
) {
    val onboardingPagesList = listOf(
        OnboardingPage(
            R.drawable.onboarding1,
            "Never miss a match",
            "Description"
        ),
        OnboardingPage(R.drawable.onboarding2, "Never miss a match", "Description"),
        OnboardingPage(R.drawable.onboarding1, "Title3", "Description")
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        onboardingPagesList.size
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { pagerIndex ->
            OnboardingPager(onboardingPage = onboardingPagesList[pagerIndex])
        }
        Spacer(modifier.weight(1f,true))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                onBoardingPageList = onboardingPagesList
            )
            FinishButton(
                modifier = Modifier,
                pagerState = pagerState
            ) {
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
    AnimatedVisibility(
        visible = pagerState.currentPage != 2,
        modifier = Modifier.fillMaxWidth(),
        exit = ExitTransition.None
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            repeat(onBoardingPageList.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)

                )
            }
        }
    }
}

@Composable
fun OnboardingPager(onboardingPage: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            painter = painterResource(id = onboardingPage.onboardingImage),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            text = onboardingPage.title,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 8.dp),
            text = onboardingPage.description,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(modifier: Modifier, pagerState: PagerState, onClick: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = pagerState.currentPage == 2,
            modifier = Modifier.fillMaxWidth(0.4f)
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "FINISH",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }

}

@Preview
@Composable
fun OnboardingPagePreview() {
    AppTheme {
        OnboardingPager(OnboardingPage(R.drawable.onboarding2, "Title", "Description"))
    }
}

@Composable
fun OnboardingPage() {

}