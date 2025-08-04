package com.kholiodev.matches.standings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

data class TeamStanding(
    val position: Int,
    val teamName: String,
    val matchesPlayed: Int,
    val wins: Int,
    val losses: Int,
    val draws: Int,
    val points: Int,
    val winRate: Float
)

@Composable
internal fun StandingsRoute(
    tournamentId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    StandingsScreen(
        tournamentId = tournamentId,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun StandingsScreen(
    tournamentId: String,
    onBackClick: () -> Unit,
    viewModel: StandingsViewModel = viewModel()
) {
    val standings by viewModel.standings.collectAsState()

    LaunchedEffect(tournamentId) {
        viewModel.loadStandings(tournamentId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tournament Standings") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Header
            item {
                StandingsHeader()
            }
            
            // Standings
            items(standings) { standing ->
                StandingRow(standing = standing)
            }
        }
    }
}

@Composable
fun StandingsHeader() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pos",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(40.dp)
            )
            Text(
                text = "Team",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "MP",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(30.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "W",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(25.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "L",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(25.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "D",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(25.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Pts",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(35.dp)
            )
        }
    }
}

@Composable
fun StandingRow(standing: TeamStanding) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${standing.position}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(40.dp),
                color = if (standing.position <= 4) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = standing.teamName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "${standing.matchesPlayed}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.width(30.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${standing.wins}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.width(25.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${standing.losses}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.width(25.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${standing.draws}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.width(25.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${standing.points}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(35.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
fun PreviewStandingsScreen() {
    MaterialTheme {
        StandingsScreen(
            tournamentId = "1",
            onBackClick = {}
        )
    }
} 