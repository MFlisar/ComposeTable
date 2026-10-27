`StyledNumberPicker` is a compound control built from `StyledInput` and
`StyledTertiaryIconButton`, so it follows the active theme without a separate
renderer registration. Supply a `NumberParser` for parsing, formatting and step
changes, and a `NumberValidator` for input validation. It supports controlled
values, a `MutableState` overload, optional increment/decrement buttons and
wrapping between the values in `overrun`. Inputs use `StyledInput` for the default style and `StyledAlternativeInput` for
the alternative style. Number pickers provide matching
`StyledNumberPicker` and `StyledAlternativeNumberPicker` functions, each with
controlled and `MutableState` value overloads.

```kotlin
val value = remember { mutableStateOf(0) }
val parser = rememberNumberParser(sample = 0, stepSize = 1)
val validator = rememberNumberValidator(value.value) { candidate ->
    if (candidate == null || candidate !in 0..100) {
        ValidationResult.Invalid("Enter a value between 0 and 100")
    } else {
        ValidationResult.Valid
    }
}

StyledNumberPicker(value, validator, parser)
```
