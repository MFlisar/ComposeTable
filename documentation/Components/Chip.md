`StyledChip` is clickable, while `StyledSelectableChip` also exposes a
`selected` state. Both accept optional leading and trailing `IconComposable`
values. `StyledChipDefaults.colors(...)` customizes the unselected `container`,
`content` and `border`, with optional `selectedContainer`, `selectedContent` and
`selectedBorder` overrides for selected chips. Omitted colors retain each
rendererâ€™s defaults. Theme renderers map these two generic behaviors to native
controls;
Material 3 defaults to `ElevatedAssistChip` and `ElevatedFilterChip` to avoid
the hover-background issue in its flat chip variants. Override that mapping by
registering a configured `Material3Chip.create(...)` through
`registerCustomComponents` with `rememberMaterial3Theme(...)`.
