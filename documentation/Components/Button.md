`StyledButton` requires a semantic `role`: `Primary`, `Secondary` or `Tertiary`.
`StyledPrimaryButton`, `StyledSecondaryButton` and `StyledTertiaryButton` provide
convenience functions. Concrete styles are selected by each theme's configurable
`roleMapping(role, context)` lambda, not by a visual variant in the Core API.
Context is owned by its source component and exposed through the open
`StyledContext` contract. `StyledSnackbar.Context.SnackbarAction` is provided
only around the snackbar action slot; `StyledDialog.Context.DialogAction` is
available for dialog action scopes. Clickable `StyledGroupBox` content maps to
each theme's outlined button style. Input leading/trailing slots provide their
own contexts; buttons preserve their standard role mapping there.

| Theme | Primary | Secondary | Tertiary | SnackbarAction |
|-------|---------|-----------|----------|---------------|
| Material 3 | Primary | FilledTonal | Text | Text for all roles |
| Cupertino | Filled | Tinted | Plain | Plain for all roles |
| Fluent 2 | Primary | Secondary | Subtle | Transparent for all roles |

Cupertino Secondary uses a translucent accent background with accent-colored
text and icons, not `onPrimary` (which belongs to the opaque Primary button).
An explicit `filledForeground` override remains supported for the tinted style.

Cupertino buttons retain `bodyMedium` typography and use compact vertical padding.
Buttons and icon buttons have a minimum 44 dp footprint, increased when the
platform's minimum interactive size is larger, subject to caller constraints.
Button content padding is inside that minimum size, not added on top of it.
Larger content and explicit caller sizes can still expand the surface.
This also applies to dropdown anchors and dialog/snackbar actions, which reuse
the same button renderers. Floating action buttons retain their Small, Standard
and Large sizes.

The snackbar source supplies its context through an internal-API local; normal
button mapping remains active outside that source scope. All three renderer
factories accept a custom mapping; delegate to `DefaultRoleMapping` for cases
that should retain the theme defaults:

```kotlin
Material3Button.create(
    roleMapping = { role, context ->
        if (context == StyledContext.Default &&
            role == StyledButton.Role.Secondary
        ) {
            Material3Button.Variant.Outlined
        } else {
            Material3Button.DefaultRoleMapping(role, context)
        }
    },
)
```
