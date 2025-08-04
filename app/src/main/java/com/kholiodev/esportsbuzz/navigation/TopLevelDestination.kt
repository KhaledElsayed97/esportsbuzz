package com.kholiodev.esportsbuzz.navigation

import com.kholiodev.esportsbuzz.R
import com.kholiodev.matches.R as matchesR
import com.kholiodev.news.R as newsR
import com.kholiodev.following.R as followingR

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val iconTextId: Int,
    val titleId: Int
) {
    MATCHES(
        selectedIcon = com.kholiodev.matches.R.drawable.ic_scores,
        unselectedIcon = com.kholiodev.matches.R.drawable.ic_scores,
        iconTextId = matchesR.string.matches,
        titleId = matchesR.string.matches
    ),
    NEWS(
        selectedIcon = com.kholiodev.news.R.drawable.ic_news,
        unselectedIcon = com.kholiodev.news.R.drawable.ic_news,
        iconTextId = newsR.string.news,
        titleId = newsR.string.news
    ),
    FOLLOWING(
        selectedIcon = com.kholiodev.following.R.drawable.ic_star,
        unselectedIcon = com.kholiodev.following.R.drawable.ic_star,
        iconTextId = com.kholiodev.following.R.string.following,
        titleId = followingR.string.following
    ),
    MORE(
        selectedIcon = com.kholiodev.more.R.drawable.ic_more,
        unselectedIcon = com.kholiodev.more.R.drawable.ic_more,
        iconTextId = com.kholiodev.more.R.string.more,
        titleId = com.kholiodev.more.R.string.more
    )
}
