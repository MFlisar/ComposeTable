`StyledInput` exposes `Default` and `Alternative` variants; each renderer maps them
to its own field appearances. It supports enabled/read-only modes, single-line or
multiline input (`singleLine = false`, `minLines`, `maxLines`), label, placeholder,
leading/trailing icons and supporting text. Shape defaults follow the implementation;
use `StyledInputDefaults.customize(CustomColors, border)` to customize colors
consistently across transparent and filled styles. Filled input uses
`filledBackground`/`filledForeground`; the default outlined input uses
`textForeground` and the optional border color.

Keyboard options/actions, visual transformation and interaction source are
forwarded to the underlying text field. Leading and trailing slots provide an
input-owned context so Styled buttons and icon buttons in those slots use the
button pointer cursor rather than inheriting the text input cursor.

For passwords combine `PasswordVisualTransformation()` with
`KeyboardOptions(keyboardType = KeyboardType.Password)`. For errors set `isError`,
provide localized `errorMessage` for accessibility and render the message in
`supportingText` when desired. Error text is not inserted automatically.

```kotlin
var text by remember { mutableStateOf("") }

StyledInput(
    value = text,
    onValueChange = { text = it },
    label = { StyledText("Name") },
    placeholder = { StyledText("Enter your name") },
    supportingText = { StyledText("Shown on your profile") },
)
```

Input decoration slots are forwarded directly; their remembered state, focus,
cursor/selection and implementation animations are not shared between renderers.
Material 3 uses native controls; Cupertino and Fluent 2 share Foundation controls
with theme-specific defaults.
