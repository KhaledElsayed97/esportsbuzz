package com.kholiodev.matches.brackets

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BracketsViewModel : ViewModel() {
    
    private val _brackets = MutableStateFlow<List<BracketRound>>(emptyList())
    val brackets: StateFlow<List<BracketRound>> = _brackets.asStateFlow()
    
    fun loadBrackets(tournamentId: String) {
        // In a real app, this would fetch data from an API
        // For now, we'll use sample data
        val sampleBrackets = createSampleBrackets(tournamentId)
        _brackets.value = sampleBrackets
    }
    
    private fun createSampleBrackets(tournamentId: String): List<BracketRound> {
        return when (tournamentId) {
            "2" -> listOf(
                BracketRound(
                    name = "Quarterfinals",
                    matches = listOf(
                        BracketMatch("QF1", "Quarterfinals", "T1", "Gen.G", "2", "1", "T1", isFinished = true),
                        BracketMatch("QF2", "Quarterfinals", "JDG", "BLG", "3", "2", "JDG", isFinished = true),
                        BracketMatch("QF3", "Quarterfinals", "G2", "FNC", "2", "3", "FNC", isFinished = true),
                        BracketMatch("QF4", "Quarterfinals", "C9", "TL", "3", "1", "C9", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Semifinals",
                    matches = listOf(
                        BracketMatch("SF1", "Semifinals", "T1", "JDG", "3", "2", "T1", isFinished = true),
                        BracketMatch("SF2", "Semifinals", "FNC", "C9", "2", "3", "C9", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Finals",
                    matches = listOf(
                        BracketMatch("F1", "Finals", "T1", "C9", "2", "1", null, isLive = true)
                    )
                )
            )
            "3" -> listOf(
                BracketRound(
                    name = "Upper Bracket Round 1",
                    matches = listOf(
                        BracketMatch("UB1", "Upper Bracket", "Team Spirit", "PSG.LGD", "3", "2", "Team Spirit", isFinished = true),
                        BracketMatch("UB2", "Upper Bracket", "OG", "Team Liquid", "2", "3", "Team Liquid", isFinished = true),
                        BracketMatch("UB3", "Upper Bracket", "Evil Geniuses", "Fnatic", "3", "1", "Evil Geniuses", isFinished = true),
                        BracketMatch("UB4", "Upper Bracket", "Alliance", "Virtus.pro", "1", "3", "Virtus.pro", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Upper Bracket Round 2",
                    matches = listOf(
                        BracketMatch("UB5", "Upper Bracket", "Team Spirit", "Team Liquid", "3", "1", "Team Spirit", isFinished = true),
                        BracketMatch("UB6", "Upper Bracket", "Evil Geniuses", "Virtus.pro", "2", "3", "Virtus.pro", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Upper Bracket Finals",
                    matches = listOf(
                        BracketMatch("UBF", "Upper Bracket Finals", "Team Spirit", "Virtus.pro", "3", "2", "Team Spirit", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Lower Bracket Round 1",
                    matches = listOf(
                        BracketMatch("LB1", "Lower Bracket", "PSG.LGD", "OG", "2", "1", "PSG.LGD", isFinished = true),
                        BracketMatch("LB2", "Lower Bracket", "Fnatic", "Alliance", "3", "0", "Fnatic", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Lower Bracket Round 2",
                    matches = listOf(
                        BracketMatch("LB3", "Lower Bracket", "Team Liquid", "PSG.LGD", "1", "2", "PSG.LGD", isFinished = true),
                        BracketMatch("LB4", "Lower Bracket", "Evil Geniuses", "Fnatic", "3", "1", "Evil Geniuses", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Lower Bracket Finals",
                    matches = listOf(
                        BracketMatch("LBF", "Lower Bracket Finals", "PSG.LGD", "Evil Geniuses", "2", "3", "Evil Geniuses", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Grand Finals",
                    matches = listOf(
                        BracketMatch("GF", "Grand Finals", "Team Spirit", "Evil Geniuses", "3", "2", "Team Spirit", isFinished = true)
                    )
                )
            )
            "4" -> listOf(
                BracketRound(
                    name = "Group Stage",
                    matches = listOf(
                        BracketMatch("GS1", "Group Stage", "Sentinels", "LOUD", "13", "11", null, isLive = true),
                        BracketMatch("GS2", "Group Stage", "Fnatic", "NRG", "13", "9", "Fnatic", isFinished = true),
                        BracketMatch("GS3", "Group Stage", "Team Liquid", "Cloud9", "11", "13", "Cloud9", isFinished = true),
                        BracketMatch("GS4", "Group Stage", "100 Thieves", "TSM", "13", "7", "100 Thieves", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Playoffs",
                    matches = listOf(
                        BracketMatch("PO1", "Playoffs", "Sentinels", "Fnatic", null, null, null),
                        BracketMatch("PO2", "Playoffs", "Cloud9", "100 Thieves", null, null, null)
                    )
                )
            )
            else -> listOf(
                BracketRound(
                    name = "Round 1",
                    matches = listOf(
                        BracketMatch("R1_1", "Round 1", "Team A", "Team B", "2", "1", "Team A", isFinished = true),
                        BracketMatch("R1_2", "Round 1", "Team C", "Team D", "1", "2", "Team D", isFinished = true),
                        BracketMatch("R1_3", "Round 1", "Team E", "Team F", "2", "0", "Team E", isFinished = true),
                        BracketMatch("R1_4", "Round 1", "Team G", "Team H", "0", "2", "Team H", isFinished = true)
                    )
                ),
                BracketRound(
                    name = "Semifinals",
                    matches = listOf(
                        BracketMatch("SF1", "Semifinals", "Team A", "Team D", null, null, null),
                        BracketMatch("SF2", "Semifinals", "Team E", "Team H", null, null, null)
                    )
                ),
                BracketRound(
                    name = "Finals",
                    matches = listOf(
                        BracketMatch("F1", "Finals", "TBD", "TBD", null, null, null)
                    )
                )
            )
        }
    }
} 