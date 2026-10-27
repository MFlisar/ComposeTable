`StyledScaffold` arranges themed bars, navigation, snackbars and floating actions
around the app content. Its body receives padding that should be applied to the
content.

```kotlin
val scope = rememberCoroutineScope()
val snackbarState = remember { StyledSnackbarHostState() }
var selectedDestination by remember { mutableStateOf(0) }

StyledScaffold(
    topBar = {
        StyledTopBar(title = { StyledText("My app") })
    },
    bottomBar = {
        StyledBottomNavigationBar {
            StyledBottomNavigationBarItem(
                selected = selectedDestination == 0,
                onClick = { selectedDestination = 0 },
                label = { StyledText("Home") },
            ) {
                StyledIcon(icon = Icons.Default.Home.toIconComposable(), contentDescription = null)
            }
            StyledBottomNavigationBarItem(
                selected = selectedDestination == 1,
                onClick = { selectedDestination = 1 },
                label = { StyledText("Settings") },
            ) {
                StyledIcon(icon = Icons.Default.Settings.toIconComposable(), contentDescription = null)
            }
        }
    },
    snackbarHost = {
        StyledSnackbarHost(snackbarState)
    },
    floatingActionButton = {
        StyledFloatingActionButton(
            onClick = {
                scope.launch {
                    val result = snackbarState.showSnackbar(
                        message = "Saved",
                        actionLabel = "Undo",
                        withDismissAction = true,
                    )
                    if (result == StyledSnackbarResult.ActionPerformed) {
                        // Undo the operation.
                    }
                }
            },
        ) {
            StyledIcon(icon = Icons.Default.Add.toIconComposable(), contentDescription = "Add")
        }
    },
) { innerPadding ->
    StyledSurface(Modifier.padding(innerPadding)) {
        StyledText("Content", Modifier.padding(StyledTheme.padding.content))
    }
}
```

Explicit color parameters follow `StyledScaffold`; shape defaults follow the
implementation's `Defaults` pattern. Always apply the supplied body
`PaddingValues`; the Foundation Scaffold also incorporates the styled horizontal
content padding. No additional generic emphasis variants or duplicate
customization objects are required for these containers.
