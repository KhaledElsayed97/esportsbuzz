package com.kholiodev.following

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.kholiodev.following.R

class FollowingViewModel(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "following_prefs", Context.MODE_PRIVATE
    )
    
    private val _games = MutableStateFlow<List<GameItem>>(emptyList())
    val games: StateFlow<List<GameItem>> = _games.asStateFlow()
    
    init {
        loadInitialGames()
    }
    
    private fun loadInitialGames() {
        val initialGames = listOf(
            GameItem(
                id = "lol",
                name = "League of Legends",
                logoResId = R.drawable.ic_lol, // Example: using existing logo resource
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969), // Gold
                        Color(0xFFFFA500)  // Brown
                    )
                )
            ),
            GameItem(
                id = "csgo",
                name = "Counter-Strike",
                logoResId = R.drawable.ic_csgo, // Example: using existing logo resource
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969), // Sea Green
                        Color.Black  // Dark Green
                    )
                )
            ),
            GameItem(
                id = "valorant",
                name = "Valorant",
                logoResId = R.drawable.ic_valo, // Example: using existing logo resource
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969), // Red
                        Color(0xFF8B0000)  // Dark Red
                    )
                )
            ),
            GameItem(
                id = "dota2",
                name = "Dota 2",
                logoResId = R.drawable.ic_dota2, // Example: using existing logo resource
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969), // Red
                        Color(0xFF8B0000)  // Navy
                    )
                )
            ),
            GameItem(
                id = "pubg",
                name = "Pubg",
                logoResId = R.drawable.ic_pubg, // Example: using existing logo resource
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969), // Gold
                        Color.Black  // Orange
                    )
                )
            ),
            GameItem(
                id = "overwatch",
                name = "Overwatch",
                logoResId = R.drawable.ic_overwatch,
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969),
                        Color(0xFFFFA500)
                    )
                )
            ),
            GameItem(
                id = "rocketleague",
                name = "Rocket League",
                logoResId = R.drawable.ic_rocket_league,
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969),
                         Color.Blue
                    )
                )
            ),
            GameItem(
                id = "rainbowsix",
                name = "Rainbow Six",
                logoResId = R.drawable.ic_r6, // Example: using existing logo resource
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF696969), // Dim Gray
                        Color(0xFF2F4F4F)  // Dark Slate Gray
                    )
                )
            )
        )
        
        _games.value = initialGames
    }
    

} 