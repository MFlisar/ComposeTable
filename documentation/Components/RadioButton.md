`StyledRadioButton` has controlled `selected` state, a nullable `onClick`, `enabled`,
`tint` and an optional interaction source. A null callback leaves interaction and
semantics to a parent, such as a selectable row.

```kotlin
var selectedOption by remember { mutableStateOf("first") }

StyledRadioButton(
    selected = selectedOption == "first",
    onClick = { selectedOption = "first" },
)
```
