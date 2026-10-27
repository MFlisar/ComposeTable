`StyledCheckbox` is a controlled Boolean component. It accepts `checked`, nullable
`onCheckedChange`, `enabled`, `tint`, `onTint` and an optional interaction source.
Give interactive controls accessible labels through caller semantics. With a null
callback, a containing `toggleable` row can own the interaction and semantics.

```kotlin
var checked by remember { mutableStateOf(false) }
StyledCheckbox(checked = checked, onCheckedChange = { checked = it })
```
