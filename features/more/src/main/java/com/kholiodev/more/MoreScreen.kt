package com.kholiodev.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun MoreRoute(
    onMenuItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MoreScreen(onMenuItemClick = onMenuItemClick)
}

@Composable
internal fun MoreScreen(
    onMenuItemClick: (String) -> Unit = {},
) {
    val menuItems = listOf(
        MenuItem(
            id = "notifications",
            title = stringResource(R.string.notifications),
            icon = Icons.Default.Notifications,
            description = "Manage your notification preferences"
        ),
        MenuItem(
            id = "terms_and_conditions",
            title = stringResource(R.string.terms_and_conditions),
            icon = Icons.Default.Info,
            description = "Read our terms and conditions"
        ),
        MenuItem(
            id = "support",
            title = stringResource(R.string.support),
            icon = Icons.Default.Email,
            description = "Get help and contact support"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItemCard(
                menuItem = menuItem,
                onClick = { onMenuItemClick(menuItem.id) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = menuItem.icon,
                        contentDescription = menuItem.title,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = menuItem.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            // Arrow indicator
            Icon(
                imageVector = Icons.Default.ArrowForward, // Using Description as a placeholder for arrow
                contentDescription = "Navigate",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

data class MenuItem(
    val id: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val description: String
)

@Preview
@Composable
fun MoreScreenPreview() {
    MaterialTheme {
        MoreScreen()
    }
}

@Preview
@Composable
fun MenuItemCardPreview() {
    MaterialTheme {
        MenuItemCard(
            menuItem = MenuItem(
                id = "notifications",
                title = "Notifications",
                icon = Icons.Default.Notifications,
                description = "Manage your notification preferences"
            ),
            onClick = {}
        )
    }
} 