Define destinations once with `StyledNavigationItem<K>` and pass the same list
to either container. Each item contains a stable unique `key`, composable `label`
and `icon`, and `enabled` (default `true`). Selection and navigation remain
caller-controlled; a null `selectedKey` leaves all destinations unselected.
Items are composed by key, so reordering retains their slot state within the
same container. Switching between rail and bottom navigation does not transfer
item-local remembered state; hoist shared state above both containers.

```kotlin
val items = remember {
    listOf(
        StyledNavigationItem(
            key = "home",
            label = { StyledText("Home") },
            icon = { StyledIcon(icon = Icons.Default.Home.toIconComposable(), contentDescription = null) },
        ),
        StyledNavigationItem(
            key = "settings",
            label = { StyledText("Settings") },
            icon = { StyledIcon(icon = Icons.Default.Settings.toIconComposable(), contentDescription = null) },
        ),
    )
}
var selectedKey by remember { mutableStateOf("home") }

StyledScaffold(
    navigationRail = {
        if (useRail) {
            StyledNavigationRail(
                items = items,
                selectedKey = selectedKey,
                onItemClick = { selectedKey = it.key },
            )
        }
    },
    bottomBar = {
        if (!useRail) {
            StyledBottomNavigationBar(
                items = items,
                selectedKey = selectedKey,
                onItemClick = { selectedKey = it.key },
            )
        }
    },
) { innerPadding ->
    StyledText("Content", Modifier.padding(innerPadding))
}
```

Both list overloads support container colors and `alwaysShowLabel`; bottom
navigation also accepts window insets, and the rail accepts a header.
Colors, indicators and layout remain renderer concerns rather than item data.
The existing content-slot APIs remain available for per-item customization.
