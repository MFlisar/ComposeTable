`StyledSwitch` is a controlled Boolean component with `checked`, nullable
`onCheckedChange`, `enabled`, `tint`, `onTint` and an optional interaction source.
A null callback leaves interaction and semantics to the parent. Give interactive
controls accessible labels through caller semantics.

The Cupertino switch uses a 51 x 31 dp track with a shadowed thumb; Fluent 2 uses
a 40 x 20 dp track with an outlined off state and hover/pressed colors. Both
animate state changes, support RTL and keyboard focus, and retain `tint` for the
checked track and `onTint` for the checked thumb.

```kotlin
var checked by remember { mutableStateOf(false) }
StyledSwitch(checked = checked, onCheckedChange = { checked = it })
```
