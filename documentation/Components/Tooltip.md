`StyledTooltip` wraps its anchor and shows a tooltip on the platform's usual
input gestures. User input is enabled by default: the Foundation renderer
supports hover, focus and touch/stylus long-press, while Material 3 delegates
these interactions to its native tooltip components. The shared state defaults
to initially hidden and non-persistent, matching Material 3.

```kotlin
val tooltipState = rememberStyledTooltipState()

StyledTooltip(
    state = tooltipState,
    tooltip = { StyledText("A short description for this control.") },
) {
    StyledButton(onClick = {}) {
        StyledText("Hover or long-press")
    }
}
```

Customize the tooltip with `shape`, `containerColor`, `contentColor`,
`maxWidth`, `shadowElevation` and `contentPadding`. Its defaults are supplied
by the active theme. `rememberStyledTooltipState(initialIsVisible, isPersistent)`
controls its initial visibility and timeout behavior; `state.show()` suspends
until dismissal and `state.dismiss()` hides it. Set `enableUserInput = false`
to disable automatic hover, focus and long-press triggers.
