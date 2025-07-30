package com.kholiodev.core.ui.components

import android.R
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EbuzzTopAppBar(
    @StringRes titleRes: Int,
    showNavigationIcon: Boolean = false,
    onNavigationClick: (() -> Unit)? = null,
    showLiveFilter: Boolean = false,
    liveFilterChecked: Boolean = false,
    onLiveFilterChange: ((Boolean) -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onCalendarClick: (() -> Unit)? = null,
    colors: TopAppBarColors = TopAppBarDefaults.smallTopAppBarColors(),
) {
    SmallTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = titleRes),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                if (showLiveFilter && onLiveFilterChange != null) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Live", style = MaterialTheme.typography.bodyMedium)
                        Switch(
                            checked = liveFilterChecked,
                            onCheckedChange = onLiveFilterChange,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        },
        actions = {
            if (onSearchClick != null) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
            if (onCalendarClick != null) {
                IconButton(onClick = onCalendarClick) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        colors = colors
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun TopAppBarPreview() {
    EbuzzTopAppBar(
        titleRes = android.R.string.untitled,
        showNavigationIcon = true,
        onNavigationClick = {},
        showLiveFilter = true,
        liveFilterChecked = true,
        onLiveFilterChange = {},
        onSearchClick = {},
        onCalendarClick = {},
    )
}