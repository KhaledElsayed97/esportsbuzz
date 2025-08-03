package com.kholiodev.matches

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MatchesViewModel : ViewModel() {
    
    private val _selectedGameFilter = MutableStateFlow("All")
    val selectedGameFilter: StateFlow<String> = _selectedGameFilter.asStateFlow()
    
    private val _availableGames = MutableStateFlow(listOf("All", "CS:GO", "League of Legends", "Dota 2", "Valorant", "Overwatch 2"))
    val availableGames: StateFlow<List<String>> = _availableGames.asStateFlow()
    
    fun updateGameFilter(selectedGame: String) {
        _selectedGameFilter.value = selectedGame
    }
    
    fun getFilteredMatches(allMatches: List<MatchItem>): List<MatchItem> {
        return if (_selectedGameFilter.value == "All") {
            allMatches
        } else {
            allMatches.filter { it.gameType == _selectedGameFilter.value }
        }
    }
}