package com.kholiodev.following

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameDetailsViewModel(context: Context, private val gameId: String) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "following_prefs", Context.MODE_PRIVATE
    )
    
    private val _gameInfo = MutableStateFlow<GameItem?>(null)
    val gameInfo: StateFlow<GameItem?> = _gameInfo.asStateFlow()
    
    private val _teams = MutableStateFlow<List<FollowableItem>>(emptyList())
    val teams: StateFlow<List<FollowableItem>> = _teams.asStateFlow()
    
    private val _players = MutableStateFlow<List<FollowableItem>>(emptyList())
    val players: StateFlow<List<FollowableItem>> = _players.asStateFlow()
    
    init {
        loadGameInfo()
        loadGameData()
        loadFollowedState()
    }
    
    private fun loadGameInfo() {
        val gameInfoMap = mapOf(
            "lol" to GameItem(
                id = "lol",
                name = "League of Legends",
                logoResId = R.drawable.ic_lol,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFFD4AF37),
                        androidx.compose.ui.graphics.Color(0xFF8B4513)
                    )
                )
            ),
            "csgo" to GameItem(
                id = "csgo",
                name = "Counter-Strike",
                logoResId = R.drawable.ic_csgo,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF2E8B57),
                        androidx.compose.ui.graphics.Color(0xFF006400)
                    )
                )
            ),
            "valorant" to GameItem(
                id = "valorant",
                name = "Valorant",
                logoResId = R.drawable.ic_valo,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFFFF6B6B),
                        androidx.compose.ui.graphics.Color(0xFF8B0000)
                    )
                )
            ),
            "dota2" to GameItem(
                id = "dota2",
                name = "Dota 2",
                logoResId = R.drawable.ic_dota2,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF4169E1),
                        androidx.compose.ui.graphics.Color(0xFF000080)
                    )
                )
            ),
            "pubg" to GameItem(
                id = "pubg",
                name = "PUBG",
                logoResId = R.drawable.ic_pubg,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFFFFD700),
                        androidx.compose.ui.graphics.Color(0xFFFFA500)
                    )
                )
            ),
            "overwatch" to GameItem(
                id = "overwatch",
                name = "Overwatch",
                logoResId = R.drawable.ic_overwatch,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF9370DB),
                        androidx.compose.ui.graphics.Color(0xFF4B0082)
                    )
                )
            ),
            "rocketleague" to GameItem(
                id = "rocketleague",
                name = "Rocket League",
                logoResId = R.drawable.ic_rocket_league,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF00CED1),
                        androidx.compose.ui.graphics.Color(0xFF008B8B)
                    )
                )
            ),
            "rainbowsix" to GameItem(
                id = "rainbowsix",
                name = "Rainbow Six",
                logoResId = R.drawable.ic_lol,
                gradient = androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        androidx.compose.ui.graphics.Color(0xFF696969),
                        androidx.compose.ui.graphics.Color(0xFF2F4F4F)
                    )
                )
            )
        )
        
        _gameInfo.value = gameInfoMap[gameId]
    }
    
    private fun loadGameData() {
        val gameData = when (gameId) {
            "lol" -> GameData(
                teams = listOf(
                    FollowableItem("t1", "T1", "South Korean esports organization", false, ItemType.TEAM),
                    FollowableItem("fnatic", "Fnatic", "Global esports brand", false, ItemType.TEAM),
                    FollowableItem("g2", "G2 Esports", "European esports organization", false, ItemType.TEAM),
                    FollowableItem("cloud9", "Cloud9", "North American esports team", false, ItemType.TEAM),
                    FollowableItem("team_liquid", "Team Liquid", "Professional esports organization", false, ItemType.TEAM)
                ),
                players = listOf(
                    FollowableItem("faker", "Faker", "Mid Laner", false, ItemType.PLAYER),
                    FollowableItem("showmaker", "ShowMaker", "Mid Laner", false, ItemType.PLAYER),
                    FollowableItem("caps", "Caps", "Mid Laner", false, ItemType.PLAYER),
                    FollowableItem("perkz", "Perkz", "ADC", false, ItemType.PLAYER),
                    FollowableItem("rekkles", "Rekkles", "ADC", false, ItemType.PLAYER)
                )
            )
            "csgo" -> GameData(
                teams = listOf(
                    FollowableItem("navi", "Natus Vincere", "Ukrainian esports organization", false, ItemType.TEAM),
                    FollowableItem("vitality", "Team Vitality", "French esports organization", false, ItemType.TEAM),
                    FollowableItem("faze", "FaZe Clan", "International esports organization", false, ItemType.TEAM),
                    FollowableItem("astralis", "Astralis", "Danish esports organization", false, ItemType.TEAM),
                    FollowableItem("liquid", "Team Liquid", "North American esports team", false, ItemType.TEAM)
                ),
                players = listOf(
                    FollowableItem("s1mple", "s1mple", "AWPer", false, ItemType.PLAYER),
                    FollowableItem("zywoo", "ZywOo", "AWPer", false, ItemType.PLAYER),
                    FollowableItem("karrigan", "karrigan", "IGL", false, ItemType.PLAYER),
                    FollowableItem("device", "device", "AWPer", false, ItemType.PLAYER),
                    FollowableItem("electronic", "electronic", "Rifler", false, ItemType.PLAYER)
                )
            )
            "valorant" -> GameData(
                teams = listOf(
                    FollowableItem("sentinels", "Sentinels", "North American esports team", false, ItemType.TEAM),
                    FollowableItem("fnatic_val", "Fnatic", "European esports team", false, ItemType.TEAM),
                    FollowableItem("loud", "LOUD", "Brazilian esports team", false, ItemType.TEAM),
                    FollowableItem("optic", "OpTic Gaming", "North American esports team", false, ItemType.TEAM),
                    FollowableItem("paper_rex", "Paper Rex", "Southeast Asian esports team", false, ItemType.TEAM)
                ),
                players = listOf(
                    FollowableItem("tenz", "Tenz", "Duelist", false, ItemType.PLAYER),
                    FollowableItem("derke", "Derke", "Duelist", false, ItemType.PLAYER),
                    FollowableItem("aspas", "aspas", "Duelist", false, ItemType.PLAYER),
                    FollowableItem("yay", "yay", "Sentinel", false, ItemType.PLAYER),
                    FollowableItem("jingg", "Jingg", "Duelist", false, ItemType.PLAYER)
                )
            )
            else -> GameData(
                teams = listOf(
                    FollowableItem("team1", "Sample Team", "Professional esports organization", false, ItemType.TEAM),
                    FollowableItem("team2", "Another Team", "Global esports brand", false, ItemType.TEAM)
                ),
                players = listOf(
                    FollowableItem("player1", "Sample Player", "Professional player", false, ItemType.PLAYER),
                    FollowableItem("player2", "Another Player", "Top tier player", false, ItemType.PLAYER)
                )
            )
        }
        
        _teams.value = gameData.teams
        _players.value = gameData.players
    }
    
    private fun loadFollowedState() {
        val followedTeams = sharedPreferences.getStringSet("followed_teams_$gameId", emptySet()) ?: emptySet()
        val followedPlayers = sharedPreferences.getStringSet("followed_players_$gameId", emptySet()) ?: emptySet()
        
        _teams.value = _teams.value.map { team ->
            team.copy(isFollowed = followedTeams.contains(team.id))
        }
        
        _players.value = _players.value.map { player ->
            player.copy(isFollowed = followedPlayers.contains(player.id))
        }
    }
    
    fun toggleTeamFollow(teamId: String) {
        val currentTeams = _teams.value.toMutableList()
        val teamIndex = currentTeams.indexOfFirst { it.id == teamId }
        
        if (teamIndex != -1) {
            val team = currentTeams[teamIndex]
            val updatedTeam = team.copy(isFollowed = !team.isFollowed)
            currentTeams[teamIndex] = updatedTeam
            _teams.value = currentTeams
            
            saveFollowedTeams()
        }
    }
    
    fun togglePlayerFollow(playerId: String) {
        val currentPlayers = _players.value.toMutableList()
        val playerIndex = currentPlayers.indexOfFirst { it.id == playerId }
        
        if (playerIndex != -1) {
            val player = currentPlayers[playerIndex]
            val updatedPlayer = player.copy(isFollowed = !player.isFollowed)
            currentPlayers[playerIndex] = updatedPlayer
            _players.value = currentPlayers
            
            saveFollowedPlayers()
        }
    }
    
    private fun saveFollowedTeams() {
        val followedTeamIds = _teams.value
            .filter { it.isFollowed }
            .map { it.id }
            .toSet()
        
        sharedPreferences.edit()
            .putStringSet("followed_teams_$gameId", followedTeamIds)
            .apply()
    }
    
    private fun saveFollowedPlayers() {
        val followedPlayerIds = _players.value
            .filter { it.isFollowed }
            .map { it.id }
            .toSet()
        
        sharedPreferences.edit()
            .putStringSet("followed_players_$gameId", followedPlayerIds)
            .apply()
    }
}

data class GameData(
    val teams: List<FollowableItem>,
    val players: List<FollowableItem>
) 