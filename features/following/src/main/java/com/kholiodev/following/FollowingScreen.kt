package com.kholiodev.following

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun FollowingRoute(
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FollowingScreen(onItemClick = onItemClick)
}

@Composable
internal fun FollowingScreen(
    onItemClick: (String) -> Unit = {},
) {
    val context = LocalContext.current
    val followingViewModel = remember { FollowingViewModel(context) }
    
    val games by followingViewModel.games.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Games Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(games) { game ->
                GameCard(
                    game = game,
                    onGameClick = { onItemClick(game.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCard(
    game: GameItem,
    onGameClick: () -> Unit
) {
    Card(
        onClick = onGameClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Game background with gradient
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = game.gradient,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
            
            // Content overlay
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Game icon/logo
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (game.logoResId != null) {
                        // Display PNG logo
                        Image(
                            painter = painterResource(id = game.logoResId),
                            contentDescription = "${game.name} logo",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(6.dp)),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        // Fallback to text icon

                    }
                }
                
                // Game name
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

data class GameItem(
    val id: String,
    val name: String,
    val logoResId: Int? = null,
    val gradient: androidx.compose.ui.graphics.Brush
)



@Preview
@Composable
fun FollowingScreenPreview() {
    FollowingScreen()
}

@Preview
@Composable
fun GameCardPreview() {
    MaterialTheme {
        GameCard(
            game = GameItem(
                id = "test",
                name = "Test Game",
                logoResId = R.drawable.ic_dota2,
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD4AF37),
                        Color(0xFF8B4513)
                    )
                )
            ),
            onGameClick = {}
        )
    }
} 