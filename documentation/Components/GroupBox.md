`StyledGroupBox` draws a border with a floating label around its content. Pass
`onClick` to make it interactive; it uses the active theme's outlined-button
renderer for button semantics, press/hover feedback and colors. The `content`
slot is not a `ColumnScope`. `contentPadding` is forwarded around the content in
both clickable and non-clickable group boxes and defaults to zero; the outlined
button's own content padding and border are disabled. The group box always draws
its border and label cutout; the outlined button supplies the theme-specific
interaction and content styling.

```kotlin
StyledGroupBox(label = "Email", onClick = { openEmail() }) {
    StyledText("name@example.com")
}
```
