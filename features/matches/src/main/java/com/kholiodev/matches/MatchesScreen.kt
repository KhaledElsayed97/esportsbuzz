package com.kholiodev.matches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
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
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kholiodev.matches.R
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

data class WeekDayItem(
    val date: LocalDate,
    val dayName: String,
    val dayNumber: String,
    val isToday: Boolean,
    val isSelected: Boolean
)

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
    val bestOf: String,
    val date: LocalDate = LocalDate.now()
)

enum class MatchStatus {
    LIVE, UPCOMING, FINISHED
}

@Composable
internal fun MatchesRoute(
    onMatchClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MatchesScreen(onMatchClick = onMatchClick)
}

@Composable
internal fun MatchesScreen(
    onMatchClick: (String) -> Unit,
    viewModel: MatchesViewModel = viewModel()
) {
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf(today) }
    
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
                bestOf = "Bo1",
                date = today
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
                bestOf = "Bo5",
                date = today
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
                bestOf = "Bo5",
                date = today.minusDays(1)
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
                bestOf = "Bo3",
                date = today.plusDays(1)
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
                bestOf = "Bo7",
                date = today.plusDays(2)
            )
        )
    }

    val selectedGameFilter by viewModel.selectedGameFilter.collectAsState()
    val availableGames by viewModel.availableGames.collectAsState()
    val filteredMatches = viewModel.getFilteredMatches(matches)
    
    // Filter matches by selected date
    val matchesForSelectedDate = filteredMatches.filter { it.date == selectedDate }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        
        // Weekday selector
        WeekDaySelector(
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        
        // Filter dropdown
        GameFilterDropdown(
            selectedGame = selectedGameFilter,
            availableGames = availableGames,
            onGameSelected = { viewModel.updateGameFilter(it) },
            modifier = Modifier.padding(16.dp)
        )
        
        // Matches list
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(matchesForSelectedDate) { match ->
                MatchItemCard(
                    match = match,
                    onClick = { onMatchClick(match.id) }
                )
            }
        }
    }
}

@Composable
fun GameFilterDropdown(
    selectedGame: String,
    availableGames: List<String>,
    onGameSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(modifier = modifier) {
        Text(
            text = "Filter by Game",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedGame,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Show dropdown menu",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .width(IntrinsicSize.Min)
            ) {
                availableGames.forEach { game ->
                    DropdownMenuItem(
                        text = { 
                            Text(
                                text = game,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (game == selectedGame) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                }
                            )
                        },
                        onClick = {
                            onGameSelected(game)
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun MatchItemCard(
    match: MatchItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 4.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header with game type and tournament
            Box(
                modifier = Modifier.fillMaxWidth()
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
                
                // Status indicator - anchored to top-right
                StatusChip(
                    status = match.status, 
                    timing = match.timing,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
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
fun StatusChip(
    status: MatchStatus, 
    timing: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (status) {
        MatchStatus.LIVE -> Color(0xFFE53E3E) // Dark red for live
        MatchStatus.UPCOMING -> Color(0xFF3182CE) // Blue for upcoming
        MatchStatus.FINISHED -> Color(0xFF38A169) // Green for finished
    }

    val textColor = Color.White
    
    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(4.dp)
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

@Composable
fun WeekDaySelector(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    val today = LocalDate.now()
    val weekDays = remember(selectedDate) {
        (-3..3).map { dayOffset ->
            val date = today.plusDays(dayOffset.toLong())
            WeekDayItem(
                date = date,
                dayName = if (date == today) "Today" else date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                dayNumber = date.dayOfMonth.toString(),
                isToday = date == today,
                isSelected = date == selectedDate
            )
        }
    }
    
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(weekDays) { weekDay ->
            WeekDayItem(
                weekDay = weekDay,
                onClick = { onDateSelected(weekDay.date) }
            )
        }
    }
}

@Composable
fun WeekDayItem(
    weekDay: WeekDayItem,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = when {
            weekDay.isSelected -> MaterialTheme.colorScheme.primary
            weekDay.isToday -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.surface
        },
        tonalElevation = if (weekDay.isSelected) 0.dp else 2.dp,
        shadowElevation = if (weekDay.isSelected) 0.dp else 1.dp,
        modifier = Modifier.width(48.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
        ) {
            Text(
                text = weekDay.dayName,
                style = MaterialTheme.typography.labelSmall,
                color = when {
                    weekDay.isSelected -> MaterialTheme.colorScheme.onPrimary
                    weekDay.isToday -> MaterialTheme.colorScheme.onPrimaryContainer
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                fontWeight = if (weekDay.isToday || weekDay.isSelected) FontWeight.Bold else FontWeight.Normal
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = weekDay.dayNumber,
                style = MaterialTheme.typography.titleMedium,
                color = when {
                    weekDay.isSelected -> MaterialTheme.colorScheme.onPrimary
                    weekDay.isToday -> MaterialTheme.colorScheme.onPrimaryContainer
                    else -> MaterialTheme.colorScheme.onSurface
                },
                fontWeight = if (weekDay.isToday || weekDay.isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

//@Preview
//@Composable
//fun PreviewMatchesScreen() {
//    MaterialTheme {
//        MatchesScreen()
//    }
//}
//
//@Preview
//@Composable
//fun PreviewMatchesScreenWithFilter() {
//    MaterialTheme {
//        val viewModel = MatchesViewModel()
//        viewModel.updateGameFilter("CS:GO")
//        MatchesScreen(viewModel = viewModel)
//    }
//}

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
                bestOf = "Bo1",
                date = LocalDate.now()
            ),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewGameFilterDropdown() {
    MaterialTheme {
        GameFilterDropdown(
            selectedGame = "All",
            availableGames = listOf("All", "CS:GO", "League of Legends", "Dota 2", "Valorant", "Overwatch 2"),
            onGameSelected = {}
        )
    }
}

@Preview
@Composable
fun PreviewWeekDaySelector() {
    MaterialTheme {
        WeekDaySelector(
            selectedDate = LocalDate.now(),
            onDateSelected = {}
        )
    }
}
