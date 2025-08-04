package com.kholiodev.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private fun formatNewsDate(date: LocalDate): String {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    
    return when {
        date == today -> "Today"
        date == yesterday -> "Yesterday"
        date.isAfter(today.minusDays(7)) -> date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        else -> date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.getDefault()))
    }
}

@Composable
internal fun NewsRoute(
    onNewsClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NewsScreen(onNewsClick = onNewsClick)
}

@Composable
internal fun NewsScreen(
    onNewsClick: (String) -> Unit = {},
) {
    val newsItems = listOf(
        NewsItem(
            id = "1",
            title = "Team Liquid Wins Major Tournament",
            imageUrl = "https://via.placeholder.com/300x200",
            date = LocalDate.now()
        ),
        NewsItem(
            id = "2", 
            title = "New Esports League Announced",
            imageUrl = "https://via.placeholder.com/300x200",
            date = LocalDate.now().minusDays(1)
        ),
        NewsItem(
            id = "3",
            title = "Player Transfer Shakes Up Competitive Scene",
            imageUrl = "https://via.placeholder.com/300x200",
            date = LocalDate.now().minusDays(2)
        ),
        NewsItem(
            id = "4",
            title = "Upcoming Tournament Schedule Released",
            imageUrl = "https://via.placeholder.com/300x200",
            date = LocalDate.now().minusDays(3)
        ),
        NewsItem(
            id = "5",
            title = "Esports Industry Growth Report",
            imageUrl = "https://via.placeholder.com/300x200",
            date = LocalDate.now().minusDays(5)
        )
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(newsItems) { newsItem ->
            NewsCard(
                newsItem = newsItem,
                onClick = { onNewsClick(newsItem.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    newsItem: NewsItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Placeholder for image - in real app, use Coil or Glide
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Image Placeholder",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Title and Date
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = newsItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = formatNewsDate(newsItem.date),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class NewsItem(
    val id: String,
    val title: String,
    val imageUrl: String,
    val date: LocalDate = LocalDate.now()
)

@Preview
@Composable
fun NewsScreenPreview() {
    NewsScreen()
} 