The `styled` artifact currently re-exports the `core` dependency and does not
include a `TableUI.Provider` implementation. It is not required for normal
ComposeTable usage.

For a ready-to-use renderer, depend on `material3` and install
`Material3TableUIProvider`. To integrate a different Compose design system,
implement `TableUI.Provider` in your application or library. See [Core](Core.md)
and [UI Providers and Customization](../Guides/UI%20Providers%20and%20Customization.md).
