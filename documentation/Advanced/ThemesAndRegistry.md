Pass a `StyledThemeProvider` to `StyledTheme`. Styled token locals are provided
first, followed by the provider's native root. The provider then calls
`*ThemeDefinition()` inside that root, allowing renderer defaults to read both
Styled and native theme locals. Finally, registry and theme name are provided
to the app content.

```kotlin
StyledTheme(
    platform = platform,
    theme = rememberFluent2Theme(),
) {
    StyledText("Hello")
}
```

For custom components, register them after the theme's default components:

```kotlin
val provider = rememberMaterial3Theme(
    customizeColorScheme = { it.copy(primary = Color.Red) },
    registerCustomComponents = {
        register(Material3Card.create())
    },
)
```

`remember*Theme()` remembers the provider using its configuration as keys;
renderer creation happens inside the provider's composition. The
`registerDefaultComponents` callback can replace the complete set of theme
defaults; `registerCustomComponents` runs afterward, so it can add components
or override a default registration, as in the example.

Registered components implement `StyledComponent<T>` and provide their
`StyledKey<T>` through `key`. Register the component instance directly with
`register(component)`; the same API is used for custom components and theme
renderers.

All themes register `StyledTopBar`, `StyledBottomNavigationBar`, `StyledNavigationRail`,
`StyledSnackbar`, `StyledFloatingActionButton`, `StyledSurface`, `StyledCheckbox`,
`StyledRadioButton`, `StyledSwitch`, `StyledInput` and `StyledProgressIndicator`. To replace one of
these implementations while keeping the other defaults, register the replacement
in `registerCustomComponents`; no additional `remember` is needed in the
registration lambda.
