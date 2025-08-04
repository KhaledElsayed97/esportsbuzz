package com.kholiodev.matches.standings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StandingsViewModel : ViewModel() {
    
    private val _standings = MutableStateFlow<List<TeamStanding>>(emptyList())
    val standings: StateFlow<List<TeamStanding>> = _standings.asStateFlow()
    
    fun loadStandings(tournamentId: String) {
        // In a real app, this would fetch data from an API
        // For now, we'll use sample data
        val sampleStandings = createSampleStandings(tournamentId)
        _standings.value = sampleStandings
    }
    
    private fun createSampleStandings(tournamentId: String): List<TeamStanding> {
        return when (tournamentId) {
            "1" -> listOf(
                TeamStanding(1, "Team Liquid", 15, 12, 2, 1, 37, 0.8f),
                TeamStanding(2, "FaZe Clan", 15, 11, 3, 1, 34, 0.733f),
                TeamStanding(3, "NAVI", 15, 10, 4, 1, 31, 0.667f),
                TeamStanding(4, "Vitality", 15, 9, 5, 1, 28, 0.6f),
                TeamStanding(5, "G2 Esports", 15, 8, 6, 1, 25, 0.533f),
                TeamStanding(6, "Cloud9", 15, 7, 7, 1, 22, 0.467f),
                TeamStanding(7, "Astralis", 15, 6, 8, 1, 19, 0.4f),
                TeamStanding(8, "Heroic", 15, 5, 9, 1, 16, 0.333f),
                TeamStanding(9, "Complexity", 15, 4, 10, 1, 13, 0.267f),
                TeamStanding(10, "BIG", 15, 3, 11, 1, 10, 0.2f)
            )
            "5" -> listOf(
                TeamStanding(1, "Dallas Fuel", 20, 16, 4, 0, 48, 0.8f),
                TeamStanding(2, "San Francisco Shock", 20, 15, 5, 0, 45, 0.75f),
                TeamStanding(3, "Seoul Dynasty", 20, 14, 6, 0, 42, 0.7f),
                TeamStanding(4, "Shanghai Dragons", 20, 13, 7, 0, 39, 0.65f),
                TeamStanding(5, "Philadelphia Fusion", 20, 12, 8, 0, 36, 0.6f),
                TeamStanding(6, "Los Angeles Gladiators", 20, 11, 9, 0, 33, 0.55f),
                TeamStanding(7, "Atlanta Reign", 20, 10, 10, 0, 30, 0.5f),
                TeamStanding(8, "Boston Uprising", 20, 9, 11, 0, 27, 0.45f),
                TeamStanding(9, "Toronto Defiant", 20, 8, 12, 0, 24, 0.4f),
                TeamStanding(10, "Vancouver Titans", 20, 7, 13, 0, 21, 0.35f)
            )
            else -> listOf(
                TeamStanding(1, "Team A", 10, 8, 2, 0, 24, 0.8f),
                TeamStanding(2, "Team B", 10, 7, 3, 0, 21, 0.7f),
                TeamStanding(3, "Team C", 10, 6, 4, 0, 18, 0.6f),
                TeamStanding(4, "Team D", 10, 5, 5, 0, 15, 0.5f),
                TeamStanding(5, "Team E", 10, 4, 6, 0, 12, 0.4f),
                TeamStanding(6, "Team F", 10, 3, 7, 0, 9, 0.3f),
                TeamStanding(7, "Team G", 10, 2, 8, 0, 6, 0.2f),
                TeamStanding(8, "Team H", 10, 1, 9, 0, 3, 0.1f)
            )
        }
    }
} 