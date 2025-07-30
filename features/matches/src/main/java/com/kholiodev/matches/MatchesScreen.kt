package com.kholiodev.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kholiodev.matches.R

data class MatchItem(
    val id: String,
    val gameType: String,
    val tournamentName: String,
    val team1Name: String,
    val team2Name: String,
    val team1Score: String,
    val team2Score: String,
    val team1Logo: Int,
    val team2Logo: Int,
    val timing: String,
    val status: MatchStatus,
    val bestOf: String
)

enum class MatchStatus {
    LIVE, UPCOMING, FINISHED
}

@Composable
internal fun MatchesRoute(
    onTopicClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MatchesScreen()
}

@Composable
internal fun MatchesScreen() {
    val matches = remember {
        listOf(
            MatchItem(
                id = "1",
                gameType = "CS:GO",
                tournamentName = "ESL Pro League Season 18",
                team1Name = "Team Liquid",
                team2Name = "FaZe Clan",
                team1Score = "16",
                team2Score = "14",
                team1Logo = R.drawable.ic_scores, // Using placeholder
                team2Logo = R.drawable.ic_scores, // Using placeholder
                timing = "LIVE",
                status = MatchStatus.LIVE,
                bestOf = "Bo1"
            ),
            MatchItem(
                id = "2",
                gameType = "League of Legends",
                tournamentName = "Worlds 2024",
                team1Name = "T1",
                team2Name = "Gen.G",
                team1Score = "2",
                team2Score = "1",
                team1Logo = R.drawable.ic_scores,
                team2Logo = R.drawable.ic_scores,
                timing = "In 2 hours",
                status = MatchStatus.UPCOMING,
                bestOf = "Bo5"
            ),
            MatchItem(
                id = "3",
                gameType = "Dota 2",
                tournamentName = "The International 2024",
                team1Name = "Team Spirit",
                team2Name = "PSG.LGD",
                team1Score = "3",
                team2Score = "2",
                team1Logo = R.drawable.ic_scores,
                team2Logo = R.drawable.ic_scores,
                timing = "Finished",
                status = MatchStatus.FINISHED,
                bestOf = "Bo5"
            ),
            MatchItem(
                id = "4",
                gameType = "Valorant",
                tournamentName = "VCT Champions 2024",
                team1Name = "Sentinels",
                team2Name = "LOUD",
                team1Score = "13",
                team2Score = "11",
                team1Logo = R.drawable.ic_scores,
                team2Logo = R.drawable.ic_scores,
                timing = "LIVE",
                status = MatchStatus.LIVE,
                bestOf = "Bo3"
            ),
            MatchItem(
                id = "5",
                gameType = "Overwatch 2",
                tournamentName = "OWL Season 2024",
                team1Name = "Dallas Fuel",
                team2Name = "San Francisco Shock",
                team1Score = "3",
                team2Score = "1",
                team1Logo = R.drawable.ic_scores,
                team2Logo = R.drawable.ic_scores,
                timing = "Tomorrow 15:00",
                status = MatchStatus.UPCOMING,
                bestOf = "Bo7"
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(matches) { match ->
            MatchItemCard(match = match)
        }
    }
}

@Composable
fun MatchItemCard(match: MatchItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header with game type and tournament
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = match.gameType,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = match.tournamentName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
                
                // Status indicator
                StatusChip(status = match.status, timing = match.timing)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Teams and scores
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Team 1
                TeamInfo(
                    teamName = match.team1Name,
                    teamScore = match.team1Score,
                    teamLogo = match.team1Logo,
                    isWinning = match.status == MatchStatus.FINISHED && match.team1Score.toIntOrNull() ?: 0 > match.team2Score.toIntOrNull() ?: 0
                )
                
                // VS or Score
                if (match.status == MatchStatus.UPCOMING) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "VS",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = match.bestOf,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${match.team1Score} - ${match.team2Score}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = match.bestOf,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
                
                // Team 2
                TeamInfo(
                    teamName = match.team2Name,
                    teamScore = match.team2Score,
                    teamLogo = match.team2Logo,
                    isWinning = match.status == MatchStatus.FINISHED && match.team2Score.toIntOrNull() ?: 0 > match.team1Score.toIntOrNull() ?: 0
                )
            }
        }
    }
}

@Composable
fun StatusChip(status: MatchStatus, timing: String) {
    val backgroundColor = when (status) {
        MatchStatus.LIVE -> Color(0xFFE53935) // Red for live
        MatchStatus.UPCOMING -> Color(0xFF1976D2) // Blue for upcoming
        MatchStatus.FINISHED -> Color(0xFF388E3C) // Green for finished
    }
    
    val textColor = Color.White
    
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = timing,
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TeamInfo(
    teamName: String,
    teamScore: String,
    teamLogo: Int,
    isWinning: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        // Team Logo
        Surface(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(teamLogo),
                    contentDescription = "$teamName logo",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        // Team Name
        Text(
            text = teamName,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isWinning) FontWeight.Bold else FontWeight.Normal,
            color = if (isWinning) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
        
        // Score (only show for live/finished matches)
        if (isWinning) {
            Text(
                text = teamScore,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun PreviewMatchesScreen() {
    MaterialTheme {
        MatchesScreen()
    }
}

@Preview
@Composable
fun PreviewMatchItemCard() {
    MaterialTheme {
        MatchItemCard(
            match = MatchItem(
                id = "1",
                gameType = "CS:GO",
                tournamentName = "ESL Pro League Season 18",
                team1Name = "Team Liquid",
                team2Name = "FaZe Clan",
                team1Score = "16",
                team2Score = "14",
                team1Logo = R.drawable.ic_scores,
                team2Logo = R.drawable.ic_scores,
                timing = "LIVE",
                status = MatchStatus.LIVE,
                bestOf = "Bo1"
            )
        )
    }
}
