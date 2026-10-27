`StyledCard` exposes only `Standard` (default) and `Alternative` variants.
  `StyledAlternativeCard` delegates to the alternative variant. Cards dispatch
  through `StyledCard.Key` and add
  `StyledCardDefaults.contentPadding` as `PaddingValues` by default (16 dp on
  all sides for Material 3 and Cupertino, 12 dp for Fluent 2),
  configured through each card renderer's `defaults(contentPadding = ...)`.
  Override `contentPadding`, for example with
  `PaddingValues(horizontal = 20.dp, vertical = 12.dp)`, per card or use `applyContentPadding = false` for
  caller-managed padding. Card padding is no longer part of `StyledPadding`.
  All support
  `shape` and optional `StyledCardDefaults.customize(colors, border, elevation)`.
  An optional `onClick` enables button semantics, keyboard activation and an
  interaction source; `enabled = false` disables the action.
  Pass the variant, adjustable defaults and optional customization directly to
  `StyledCard`; there is no separate style wrapper.
  Filled styles use `CustomColors.filledBackground` and
  `CustomColors.filledForeground`; outlined styles use `textForeground` and
  the optional border color. Elevation is part of customization because it is
  style-specific: Material 3 uses a supplied elevation for filled cards and
  otherwise retains its native default; outlined cards and Cupertino/Fluent 2
  renderers use their own elevation behavior.
  Material 3 maps Standard to `Card` and Alternative to `OutlinedCard`, selecting
  the clickable overload only when `onClick` is supplied. Cupertino and Fluent 2
  map Alternative to their outlined appearance and share Foundation rendering.
  All theme factories expose a `card` implementation parameter.
  Card APIs now live in the `components` package, rather than `common`.
