Supply `navigationRail` to `StyledScaffold` to place navigation at the logical
start (left in LTR, right in RTL), between the top and bottom bars. The supplied
body padding includes the measured rail width. Continue applying that padding
to the body; the rail needs no additional system-bar padding inside Scaffold.
FABs and snackbars remain Scaffold-wide overlays.
Choose rail versus bottom navigation explicitly, for example using your own
window-size breakpoint. Scaffold does not switch automatically.

```kotlin
StyledScaffold(
    navigationRail = {
        StyledNavigationRail(header = { StyledText("My app") }) {
            StyledNavigationRailItem(
                selected = selectedDestination == 0,
                onClick = { selectedDestination = 0 },
                label = { StyledText("Home") },
            ) {
                StyledIcon(icon = Icons.Default.Home.toIconComposable(), contentDescription = null)
            }
        }
    },
) { innerPadding ->
    StyledText("Content", Modifier.padding(innerPadding))
}
```

Rail items offer the same selection, enabled, label, color and interaction-source
options as bottom navigation items. Material3 uses `NavigationRail` and
`NavigationRailItem`; Cupertino and Fluent2 share an internal Foundation renderer.
The Cupertino rail is an adaptive sidebar, not a native UIKit component.
