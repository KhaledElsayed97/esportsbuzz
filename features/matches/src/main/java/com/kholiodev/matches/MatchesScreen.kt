package com.kholiodev.matches

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun MatchesRoute(
    onTopicClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    MatchesScreen(
    )
}

@Composable
internal fun MatchesScreen(
) {
    Text("Matches Screen")
}

@Preview
@Composable
fun PreviewMatchesScreen(){
    MatchesScreen()
}
