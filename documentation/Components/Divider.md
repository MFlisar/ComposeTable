`StyledHorizontalDivider` and `StyledVerticalDivider` are generic Foundation
components in `com.michaelflisar.composestyledcomponents.common`; they work with
all themes without an additional registry entry. Both accept `modifier`,
`thickness` (default `1.dp`) and `color` (default
`StyledTheme.colorScheme.outlineVariant`). Use `Dp.Hairline` for a
one-physical-pixel divider. Thickness must be finite and non-negative.

```kotlin
StyledHorizontalDivider()
StyledHorizontalDivider(
    modifier = Modifier.padding(
        start = StyledTheme.padding.content.calculateStartPadding(LocalLayoutDirection.current),
        end = StyledTheme.padding.content.calculateEndPadding(LocalLayoutDirection.current),
    ),
    thickness = 2.dp,
    color = StyledTheme.colorScheme.primary,
)
Row(Modifier.height(IntrinsicSize.Min)) {
    StyledText("Left")
    StyledVerticalDivider()
    StyledText("Right")
}
```

Horizontal dividers fill the available width; vertical dividers fill the
available height. Constrain a vertical divider with `Modifier.height(...)`,
or use `IntrinsicSize.Min` on a wrapping row as above. Dividers add no spacing;
apply padding through the modifier or arrange spacing in the parent.
