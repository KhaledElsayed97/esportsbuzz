package com.kholiodev.matches.brackets

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

data class BracketMatch(
    val id: String,
    val round: String,
    val team1: String,
    val team2: String,
    val team1Score: String?,
    val team2Score: String?,
    val winner: String?,
    val isLive: Boolean = false,
    val isFinished: Boolean = false
)

data class BracketRound(
    val name: String,
    val matches: List<BracketMatch>
)

@Composable
internal fun BracketsRoute(
    tournamentId: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BracketsScreen(
        tournamentId = tournamentId,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BracketsScreen(
    tournamentId: String,
    onBackClick: () -> Unit,
    viewModel: BracketsViewModel = viewModel()
) {
    val brackets by viewModel.brackets.collectAsState()

    LaunchedEffect(tournamentId) {
        viewModel.loadBrackets(tournamentId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tournament Brackets") },
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(brackets) { round ->
                BracketRoundCard(round = round)
            }
        }
    }
}

@Composable
fun BracketRoundCard(round: BracketRound) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = round.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                round.matches.forEach { match ->
                    BracketMatchItem(match = match)
                }
            }
        }
    }
}

@Composable
fun BracketMatchItem(match: BracketMatch) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                match.isLive -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                match.isFinished -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                else -> MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            // Team 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = match.team1,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (match.winner == match.team1) FontWeight.Bold else FontWeight.Normal,
                    color = if (match.winner == match.team1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                match.team1Score?.let { score ->
                    Text(
                        text = score,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (match.winner == match.team1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            // Team 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = match.team2,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (match.winner == match.team2) FontWeight.Bold else FontWeight.Normal,
                    color = if (match.winner == match.team2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                )
                match.team2Score?.let { score ->
                    Text(
                        text = score,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (match.winner == match.team2) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            
            // Status indicator
            if (match.isLive) {
                Text(
                    text = "LIVE",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            } else if (match.isFinished) {
                Text(
                    text = "Finished",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewBracketsScreen() {
    MaterialTheme {
        BracketsScreen(
            tournamentId = "1",
            onBackClick = {}
        )
    }
} 