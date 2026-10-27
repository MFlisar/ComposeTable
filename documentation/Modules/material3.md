# Material 3 Module

The `material3` module provides `Material3TableUIProvider`, a
`TableUI.Provider` implementation backed by Compose Material 3 components.
Choose it when you want ComposeTable to use Material 3 directly, without
ComposeStyledComponents.

Add the `core` and `material3` artifacts, then install the provider once before
rendering tables:

<!-- snippet: InstallMaterial3Provider -->
```kt
TableUI.set(Material3TableUIProvider)
```
<!-- endSnippet -->

Render the table under `MaterialTheme`. The provider reads Material theme
colors, shape, typography, and content color while drawing the table.

Use one UI implementation per application: choose `material3` for this
standalone Material 3 provider, or choose [`styled`](styled.md) to render through
ComposeStyledComponents, including its Material 3, Fluent 2, and Cupertino
styles. Core does not include a default renderer, so the selected provider must
be installed with `TableUI.set(...)`; the setting is global and applies to all
tables in the process.

See [Getting Started](../Guides/getting-started.md) for a complete table
example and [UI Providers and Customization](../Guides/ui-providers-and-customization.md)
for the provider contract.
