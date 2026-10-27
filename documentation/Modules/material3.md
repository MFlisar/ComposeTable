# Material 3 Module

The `material3` module provides `Material3TableUIProvider`, a
`TableUI.Provider` implementation backed by Compose Material 3 components.
Use it when the application already uses a Material 3 design system.

Add both the `core` and `material3` artifacts, then install the provider before
rendering a table:

<!-- snippet: InstallMaterial3Provider -->
```kt
TableUI.set(Material3TableUIProvider)
```
<!-- endSnippet -->

Render the table under `MaterialTheme`. The provider reads Material theme
colors, shape, typography, and content color while drawing the table.

`material3` depends on `core` and supplies Material 3 text fields, buttons,
chips, menus, dialogs, indicators, dividers, and selection controls used by the
table UI. It is an optional implementation: applications using another design
system can provide their own `TableUI.Provider`.

See [Getting Started](../Guides/getting-started.md) for a complete table
example and [UI Providers and Customization](../Guides/ui-providers-and-customization.md)
for the provider contract.
