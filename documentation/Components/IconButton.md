`StyledIconButton` is a separately registered control with the semantic roles
`Primary`, `Secondary` and `Tertiary`, matching `StyledButton`. Each theme maps
these roles to its own icon-button styles. It supports `shape`, `interactionSource`
and `StyledIconButtonDefaults.customize(CustomColors, border)`, and is registered by all three built-in
themes. The content overload accepts an arbitrary composable slot; the
`IconComposable` overload applies the configured icon size and optional padding.
Use `StyledPrimaryIconButton`, `StyledSecondaryIconButton` or
`StyledTertiaryIconButton` as convenience functions.

| Theme | Primary | Secondary | Tertiary |
|-------|---------|-----------|----------|
| Material 3 | Filled | Outlined | Standard |
| Cupertino | Filled | Filled tonal | Standard |
| Fluent 2 | Filled | Neutral secondary | Standard |

Fluent 2 buttons and icon buttons share an internal color resolver for default,
hover, pressed and disabled states. Secondary uses a neutral surface and a
contrasting neutral foreground in both controls, rather than a Primary-tinted
icon surface with Primary-colored content. The icon renderer's existing
`FilledTonal` variant name is retained and now selects this neutral secondary
appearance. Both controls honor `filledBackground`/`filledForeground` overrides
for Primary and Secondary, and `textForeground` for transparent/outlined styles.

```kotlin
StyledIconButton(
    icon = Icons.Default.Add.toIconComposable(),
    contentDescription = "Add item",
    onClick = { addItem() },
)
```

Use `CustomColors` for role-aware customization: filled styles use
`filledBackground`/`filledForeground`, while standard or outlined styles use
`textForeground`; `border` optionally overrides the outline.
