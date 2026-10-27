`StyledPadding.content` also uses `PaddingValues`, with 16 dp on all sides on
mobile and 12 dp on desktop by default. Pass it directly to
`Modifier.padding(StyledTheme.padding.content)`. For horizontal-only padding,
resolve start/end with `calculateStartPadding`/`calculateEndPadding` using the
current `LocalLayoutDirection`; resolve individual vertical edges with
`calculateTopPadding`/`calculateBottomPadding`. The `small`, `medium` and `large`
tokens remain `Dp`.
