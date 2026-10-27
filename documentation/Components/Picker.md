`StyledPicker` displays non-editable text with a clickable end icon. It is
composed from `StyledInput` and `StyledInputAction`, so it uses the active theme
without a separate renderer. Provide an `IconComposable` (for example, with
`Icons.Default.ArrowDropDown.toIconComposable()`),
and handle the action with `onStartPicker`. The picker accepts a controlled
string and supports both input variants. Set
`clickOnIconOnly = false` to make the entire field clickable instead of only its
icon. The default comes from `StyledPickerDefaults.clickOnIconOnly` and is `true`.

```kotlin
StyledPicker(
    value = selectedOption,
    onStartPicker = { openOptions() },
    icon = Icons.Default.ArrowDropDown.toIconComposable(),
    label = "Selection",
)
```
