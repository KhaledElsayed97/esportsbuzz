package com.kholiodev.matches.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kholiodev.matches.R
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.launch

sealed class TeamLogo {
    data class Resource(val resId: Int) : TeamLogo()
    data class Url(val url: String) : TeamLogo()
}

data class Player(
    val id: String,
    val name: String,
    val role: String,
    val kda: String? = null,
    val photo: Int = R.drawable.ic_scores // Placeholder
)

data class TeamLineup(
    val teamName: String,
    val teamLogo: TeamLogo,
    val players: List<Player>,
    val coach: String
)

data class MatchDetails(
    val id: String,
    val gameType: String,
    val tournamentName: String,
    val tournamentType: TournamentType,
    val team1: TeamLineup,
    val team2: TeamLineup,
    val team1Score: String,
    val team2Score: String,
    val status: MatchStatus,
    val timing: String,
    val bestOf: String,
    val map: String? = null,
    val isNotified: Boolean = false
)

enum class TournamentType {
    LEAGUE, PLAYOFFS, TOURNAMENT
}

enum class MatchStatus {
    LIVE, UPCOMING, FINISHED
}

@Composable
internal fun MatchDetailsRoute(
    matchId: String,
    onBackClick: () -> Unit,
    onViewStandings: (String) -> Unit,
    onViewBrackets: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MatchDetailsScreen(
        matchId = matchId,
        onBackClick = onBackClick,
        onViewStandings = onViewStandings,
        onViewBrackets = onViewBrackets
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MatchDetailsScreen(
    matchId: String,
    onBackClick: () -> Unit,
    onViewStandings: (String) -> Unit,
    onViewBrackets: (String) -> Unit,
    viewModel: MatchDetailsViewModel = viewModel()
) {
    val matchDetails by viewModel.matchDetails.collectAsState()
    val isNotified by viewModel.isNotified.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(matchId) {
        viewModel.loadMatchDetails(matchId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Match Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        viewModel.toggleNotification()
                        coroutineScope.launch {
                            val message = if (isNotified) "Notifications disabled" else "Notifications enabled"
                            snackbarHostState.showSnackbar(message = message)
                        }
                    }) {
                        Icon(
                            if (isNotified) Icons.Default.Notifications else Icons.Default.Notifications,
                            contentDescription = "Toggle notifications",
                            tint = if (isNotified) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            matchDetails?.let { match ->
                // Match Header
                item {
                    MatchHeader(match = match)
                }

                // Teams and Score
                item {
                    TeamsScoreCard(match = match)
                }

                // Team 1 Lineup
                item {
                    TeamLineupCard(
                        team = match.team1,
                        isWinning = match.status == MatchStatus.FINISHED && 
                                  match.team1Score.toIntOrNull() ?: 0 > match.team2Score.toIntOrNull() ?: 0
                    )
                }

                // Team 2 Lineup
                item {
                    TeamLineupCard(
                        team = match.team2,
                        isWinning = match.status == MatchStatus.FINISHED && 
                                  match.team2Score.toIntOrNull() ?: 0 > match.team1Score.toIntOrNull() ?: 0
                    )
                }

                // Match Stats (if live or finished)
                if (match.status != MatchStatus.UPCOMING) {
                    item {
                        MatchStatsCard(match = match)
                    }
                }

                // Tournament Info
                item {
                    TournamentInfoCard(
                        match = match,
                        onViewStandings = onViewStandings,
                        onViewBrackets = onViewBrackets
                    )
                }
            }
        }
    }
}

@Composable
fun MatchHeader(match: MatchDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = match.gameType,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = match.tournamentName,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                StatusChip(
                    status = match.status,
                    timing = match.timing
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = match.bestOf,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                
                match.map?.let { map ->
                    Text(
                        text = "Map: $map",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun TournamentInfoCard(
    match: MatchDetails,
    onViewStandings: (String) -> Unit,
    onViewBrackets: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Tournament Info",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            // Tournament Title
            Text(
                text = match.tournamentName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when (match.tournamentType) {
                    TournamentType.LEAGUE -> {
                        Button(
                            onClick = { onViewStandings(match.id) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("View Standings")
                        }
                    }
                    TournamentType.PLAYOFFS, TournamentType.TOURNAMENT -> {
                        Button(
                            onClick = { onViewBrackets(match.id) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(
                                Icons.Default.Star,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("View Brackets")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TeamsScoreCard(match: MatchDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Team 1
                TeamInfo(
                    teamName = match.team1.teamName,
                    teamLogo = match.team1.teamLogo,
                    isWinning = match.status == MatchStatus.FINISHED && 
                              match.team1Score.toIntOrNull() ?: 0 > match.team2Score.toIntOrNull() ?: 0
                )
                
                // Score
                if (match.status == MatchStatus.UPCOMING) {
                    Text(
                        text = "VS",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Text(
                        text = "${match.team1Score} - ${match.team2Score}",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                // Team 2
                TeamInfo(
                    teamName = match.team2.teamName,
                    teamLogo = match.team2.teamLogo,
                    isWinning = match.status == MatchStatus.FINISHED && 
                              match.team2Score.toIntOrNull() ?: 0 > match.team1Score.toIntOrNull() ?: 0
                )
            }
        }
    }
}

@Composable
fun TeamInfo(
    teamName: String,
    teamLogo: TeamLogo,
    isWinning: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(12.dp)),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                when (teamLogo) {
                    is TeamLogo.Resource -> {
                        Icon(
                            painter = painterResource(teamLogo.resId),
                            contentDescription = "$teamName logo",
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    is TeamLogo.Url -> {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(teamLogo.url)
                                .crossfade(true)
                                .build(),
                            contentDescription = "$teamName logo",
                            modifier = Modifier.size(32.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = teamName,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isWinning) FontWeight.Bold else FontWeight.Normal,
            color = if (isWinning) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}

@Composable
fun TeamLineupCard(
    team: TeamLineup,
    isWinning: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isWinning) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = team.teamName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isWinning) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                
                if (isWinning) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Winner",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Players
            team.players.forEach { player ->
                PlayerRow(player = player)
                Spacer(modifier = Modifier.height(8.dp))
            }
            
            // Coach
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Coach",
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Column {
                        Text(
                            text = team.coach,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Coach",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerRow(player: Player) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(player.photo),
                        contentDescription = "${player.name} photo",
                        modifier = Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Column {
                Text(
                    text = player.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = player.role,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        player.kda?.let { kda ->
            Text(
                text = kda,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun MatchStatsCard(match: MatchDetails) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Match Statistics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            // Placeholder stats - in real app, these would come from API
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Duration", "45:30")
                StatItem("Rounds", "30")
                StatItem("MVP", "Player1")
            }
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun StatusChip(
    status: MatchStatus,
    timing: String
) {
    val backgroundColor = when (status) {
        MatchStatus.LIVE -> Color(0xFFE53E3E)
        MatchStatus.UPCOMING -> Color(0xFF3182CE)
        MatchStatus.FINISHED -> Color(0xFF38A169)
    }

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = timing,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewMatchDetailsScreen() {
    MaterialTheme {
        MatchDetailsScreen(
            matchId = "1",
            onBackClick = {},
            onViewStandings = {},
            onViewBrackets = {}
        )
    }
} 