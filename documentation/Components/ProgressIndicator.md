`StyledProgressIndicator` supports circular and linear renderings. The
`CircularProgressIndicator` and `LinearProgressIndicator` convenience functions
select the matching type. A `null` progress value displays an indeterminate
indicator; values from `0f` to `1f` display determinate progress. Values outside
that range are clamped.

```kotlin
CircularProgressIndicator()
LinearProgressIndicator(progress = 0.65f, modifier = Modifier.fillMaxWidth())
```
