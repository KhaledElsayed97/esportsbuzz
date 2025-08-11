package com.kholiodev.matches.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.kholiodev.matches.R

class MatchDetailsViewModel : ViewModel() {
    
    private val _matchDetails = MutableStateFlow<MatchDetails?>(null)
    val matchDetails: StateFlow<MatchDetails?> = _matchDetails.asStateFlow()
    
    private val _isNotified = MutableStateFlow(false)
    val isNotified: StateFlow<Boolean> = _isNotified.asStateFlow()
    
    fun loadMatchDetails(matchId: String) {
        // In a real app, this would fetch data from an API
        // For now, we'll use sample data based on the match ID
        val sampleMatch = createSampleMatchDetails(matchId)
        _matchDetails.value = sampleMatch
        _isNotified.value = sampleMatch.isNotified
    }
    
    fun toggleNotification() {
        _isNotified.update { !it }
        // In a real app, this would also update the backend
    }
    
    private fun createSampleMatchDetails(matchId: String): MatchDetails {
        return when (matchId) {
            "1" -> MatchDetails(
                id = "1",
                gameType = "CS:GO",
                tournamentName = "ESL Pro League Season 18",
                tournamentType = TournamentType.LEAGUE,
                team1 = TeamLineup(
                    teamName = "Team Liquid",
                    teamLogo = TeamLogo.Url("https://i.postimg.cc/FK4PTPSW/Team-Liquidlogo-square-Dark.webp"),
                    players = listOf(
                        Player("1", "EliGE", "Rifler", "25/15/8"),
                        Player("2", "NAF", "Rifler", "22/18/6"),
                        Player("3", "oSee", "AWPer", "18/20/4"),
                        Player("4", "YEKINDAR", "Entry", "20/16/7"),
                        Player("5", "nitr0", "IGL", "15/22/12")
                    ),
                    coach = "adreN"
                ),
                team2 = TeamLineup(
                    teamName = "G2 Esports",
                    teamLogo = TeamLogo.Url("https://i.postimg.cc/RV2pHCZz/G2-Esports-2020-lightmode.png"),
                    players = listOf(
                        Player("6", "karrigan", "IGL", "16/24/10"),
                        Player("7", "rain", "Rifler", "19/19/5"),
                        Player("8", "broky", "AWPer", "21/17/3"),
                        Player("9", "Twistzz", "Rifler", "23/16/8"),
                        Player("10", "ropz", "Rifler", "24/15/9")
                    ),
                    coach = "RobbaN"
                ),
                team1Score = "10",
                team2Score = "6",
                status = MatchStatus.LIVE,
                timing = "LIVE",
                bestOf = "Bo1",
                map = "Mirage",
                isNotified = true
            )
            "2" -> MatchDetails(
                id = "2",
                gameType = "League of Legends",
                tournamentName = "Worlds 2024",
                tournamentType = TournamentType.TOURNAMENT,
                team1 = TeamLineup(
                    teamName = "T1",
                    teamLogo = TeamLogo.Url("https://i.postimg.cc/jSmTCYGW/img-faker.jpg"),
                    players = listOf(
                        Player("11", "Zeus", "Top", "3/2/8"),
                        Player("12", "Oner", "Jungle", "2/3/12"),
                        Player("13", "Faker", "Mid", "8/1/6"),
                        Player("14", "Gumayusi", "ADC", "12/2/4"),
                        Player("15", "Keria", "Support", "1/4/15")
                    ),
                    coach = "Bengi"
                ),
                team2 = TeamLineup(
                    teamName = "Gen.G",
                    teamLogo = TeamLogo.Url("https://prnt.sc/sokvWocPZH05"),
                    players = listOf(
                        Player("16", "Doran", "Top", "2/4/6"),
                        Player("17", "Peanut", "Jungle", "3/5/8"),
                        Player("18", "Chovy", "Mid", "6/3/7"),
                        Player("19", "Peyz", "ADC", "8/4/5"),
                        Player("20", "Delight", "Support", "0/6/12")
                    ),
                    coach = "Score"
                ),
                team1Score = "2",
                team2Score = "1",
                status = MatchStatus.UPCOMING,
                timing = "In 2 hours",
                bestOf = "Bo5",
                isNotified = false
            )
            "3" -> MatchDetails(
                id = "3",
                gameType = "Dota 2",
                tournamentName = "The International 2024",
                tournamentType = TournamentType.PLAYOFFS,
                team1 = TeamLineup(
                    teamName = "Team Spirit",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("21", "Yatoro", "Carry", "15/3/8"),
                        Player("22", "Larl", "Mid", "8/5/12"),
                        Player("23", "Collapse", "Offlane", "4/7/15"),
                        Player("24", "Mira", "Support", "2/8/18"),
                        Player("25", "Miposhka", "Support", "1/9/20")
                    ),
                    coach = "Silent"
                ),
                team2 = TeamLineup(
                    teamName = "PSG.LGD",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("26", "shiro", "Carry", "12/6/7"),
                        Player("27", "NothingToSay", "Mid", "7/4/10"),
                        Player("28", "Faith_bian", "Offlane", "3/8/14"),
                        Player("29", "XinQ", "Support", "1/10/16"),
                        Player("30", "y`", "Support", "0/11/18")
                    ),
                    coach = "xiao8"
                ),
                team1Score = "3",
                team2Score = "2",
                status = MatchStatus.FINISHED,
                timing = "Finished",
                bestOf = "Bo5",
                isNotified = true
            )
            "4" -> MatchDetails(
                id = "4",
                gameType = "Valorant",
                tournamentName = "VCT Champions 2024",
                tournamentType = TournamentType.TOURNAMENT,
                team1 = TeamLineup(
                    teamName = "Sentinels",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("31", "TenZ", "Duelist", "18/12/4"),
                        Player("32", "ShahZaM", "IGL", "12/15/8"),
                        Player("33", "dapr", "Sentinel", "8/16/12"),
                        Player("34", "SicK", "Flex", "14/13/6"),
                        Player("35", "zombs", "Controller", "6/18/14")
                    ),
                    coach = "Rawkus"
                ),
                team2 = TeamLineup(
                    teamName = "LOUD",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("36", "aspas", "Duelist", "16/14/5"),
                        Player("37", "saadhak", "IGL", "11/16/9"),
                        Player("38", "Less", "Sentinel", "7/17/13"),
                        Player("39", "cauanzin", "Flex", "13/14/7"),
                        Player("40", "tuyz", "Controller", "5/19/15")
                    ),
                    coach = "fRoD"
                ),
                team1Score = "13",
                team2Score = "11",
                status = MatchStatus.LIVE,
                timing = "LIVE",
                bestOf = "Bo3",
                map = "Ascent",
                isNotified = false
            )
            "5" -> MatchDetails(
                id = "5",
                gameType = "Overwatch 2",
                tournamentName = "OWL Season 2024",
                tournamentType = TournamentType.LEAGUE,
                team1 = TeamLineup(
                    teamName = "Dallas Fuel",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("41", "Fearless", "Tank", "15/8/12"),
                        Player("42", "Sp9rk1e", "DPS", "22/10/8"),
                        Player("43", "Doha", "DPS", "18/12/10"),
                        Player("44", "Fielder", "Support", "5/15/25"),
                        Player("45", "ChiYo", "Support", "3/18/28")
                    ),
                    coach = "Rush"
                ),
                team2 = TeamLineup(
                    teamName = "San Francisco Shock",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("46", "Coluge", "Tank", "12/10/14"),
                        Player("47", "Proper", "DPS", "20/11/9"),
                        Player("48", "s9mm", "DPS", "16/13/11"),
                        Player("49", "Violet", "Support", "4/16/26"),
                        Player("50", "FiNN", "Support", "2/19/29")
                    ),
                    coach = "Crusty"
                ),
                team1Score = "3",
                team2Score = "1",
                status = MatchStatus.UPCOMING,
                timing = "Tomorrow 15:00",
                bestOf = "Bo7",
                isNotified = true
            )
            else -> MatchDetails(
                id = matchId,
                gameType = "Unknown Game",
                tournamentName = "Unknown Tournament",
                tournamentType = TournamentType.LEAGUE,
                team1 = TeamLineup(
                    teamName = "Team A",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("1", "Player 1", "Role 1"),
                        Player("2", "Player 2", "Role 2"),
                        Player("3", "Player 3", "Role 3"),
                        Player("4", "Player 4", "Role 4"),
                        Player("5", "Player 5", "Role 5")
                    ),
                    coach = "Coach A"
                ),
                team2 = TeamLineup(
                    teamName = "Team B",
                    teamLogo = TeamLogo.Resource(R.drawable.ic_scores),
                    players = listOf(
                        Player("6", "Player 6", "Role 1"),
                        Player("7", "Player 7", "Role 2"),
                        Player("8", "Player 8", "Role 3"),
                        Player("9", "Player 9", "Role 4"),
                        Player("10", "Player 10", "Role 5")
                    ),
                    coach = "Coach B"
                ),
                team1Score = "0",
                team2Score = "0",
                status = MatchStatus.UPCOMING,
                timing = "TBD",
                bestOf = "Bo1",
                isNotified = false
            )
        }
    }
} 