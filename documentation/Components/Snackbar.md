Snackbars support action/dismiss slots and an action on a separate line.
  Cupertino and Fluent 2 expose `contentPadding` in their renderer `defaults(...)`
  factories (by default, `padding.large` horizontally and `padding.small` vertically).
  For example, configure the theme with
  `snackbar = CupertinoSnackbar.create(defaults = CupertinoSnackbar.defaults(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)))`.
  This controls the snackbar's inner padding, including host-created snackbars;
  host outer padding is unchanged. Material 3 retains its native snackbar padding.
  The standard dismiss action uses `StyledTertiaryIconButton`. Cupertino and
  Fluent 2 derive the minimum content-row height directly from the larger of
  `StyledTheme.sizes.minimumInteractiveSize` and `iconSize`, keeping single-line
  messages equally tall with or without that icon. Multiline text and larger
  custom slots can grow.
  `StyledSnackbar(data)` provides standard actions; `StyledSnackbarHost` also
  accepts a custom snackbar renderer. Pass localized dismiss and pane labels to
  the host when needed.

`showSnackbar` suspends until action or dismissal and queues concurrent calls.
Cancelling its coroutine removes that message. Without an action, duration
defaults to `Short`; with an action it defaults to `Indefinite`, so provide a
dismiss action or dismiss programmatically. Explicit `Short` or `Long` durations
are also supported. Finite durations honor accessibility timeout recommendations.
The host keeps outgoing text visible during the exit animation. Material 3
delegates timing, accessibility and transitions to its native `SnackbarHost`.
Cupertino and Fluent 2 share a Foundation host that fades and scales the snackbar
from its center, without animating its measured layout size. All theme definition
factories expose a `snackbarHost`
implementation parameter; scoped overrides use `StyledSnackbarHost.Key`.
The built-in hosts forward the custom snackbar slot; default content still uses
the registered `StyledSnackbar` renderer in every theme.

Cupertino and Fluent 2 snackbars are adaptive extensions, not native UIKit controls.
