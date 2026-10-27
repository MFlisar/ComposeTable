`StyledTabRow` and `StyledTab` provide themed tab navigation. Choose a primary
or secondary row, with either fixed or horizontally scrollable tabs. Selection
is controlled by the caller, and tab content can be any composable:

```kotlin
val selectedTab = remember { mutableStateOf(0) }
val tabs = listOf("Overview", "Details")

StyledPrimaryTabRow(selectedTabIndex = selectedTab.value) {
    tabs.forEachIndexed { index, title ->
        StyledTab(
            selected = selectedTab.value == index,
            onClick = { selectedTab.value = index },
        ) {
            StyledText(title)
        }
    }
}

StyledSecondaryTabRow(selectedTabIndex = selectedTab.value) {
    tabs.forEachIndexed { index, title ->
        StyledTab(
            selected = selectedTab.value == index,
            onClick = { selectedTab.value = index },
        ) {
            StyledText(title)
        }
    }
}

// For tabs that exceed the available width:
StyledPrimaryScrollableTabRow(selectedTabIndex = selectedTab.value) {
    tabs.forEachIndexed { index, title ->
        StyledTab(
            selected = selectedTab.value == index,
            onClick = { selectedTab.value = index },
        ) {
            StyledText(title)
        }
    }
}
// Use StyledSecondaryScrollableTabRow for the scrollable secondary variant.
```

`StyledPager` uses these components for its tab row, so theme overrides affect
both standalone tabs and pager navigation.
