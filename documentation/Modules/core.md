# ComposeTable Core

ComposeTable Core provides the table composable, data models, typed columns,
query and selection state, and the UI-provider contract. It is the base module
for every ComposeTable table and does not depend on Material 3.

## Why Core and Material 3 are separate

Core is built on Compose Foundation rather than a specific design system. This
keeps table behavior and layout independent from the visual components used to
render it, so an application can use the built-in Material 3 provider or supply
an implementation for another design system.

This separation is also useful for
[ComposeStyledComponents](https://github.com/MFlisar/ComposeStyledComponents),
which can render UI in Material 3, Fluent 2, or Cupertino styles. Keeping
ComposeTable's core UI framework-neutral makes it possible for libraries to
integrate with those different renderers without taking a Material 3 dependency.

The trade-off is that Core has no default renderer: an application must install
a `TableUI.Provider` with `TableUI.set(...)` before rendering a table. The
provider is global, so this is typically done once during application setup.

## Choose a data source

Create a local model with `rememberTable(initialItems = ...)` for data already
available in memory. Use the `TableDataProvider` overload when data should be
counted and loaded on demand, such as a database or remote service. Both models
use the same `TableModel`, `TableColumn`, `TableState`, and `Table` APIs.

## Provide a renderer

Core defines `TableUI.Provider`, the contract for the text, controls, layout,
colors, and shape used by the table. Install an implementation with
`TableUI.set(...)` before rendering. The `material3` module includes the built-in
`Material3TableUIProvider`; an application can provide a different implementation
by implementing `TableUI.Provider`.

The provider is global, not scoped to a composition. Configure it once for the
application; tables in the same process use that provider.

## Next steps

- [Get started](../Guides/getting-started.md) with a local table.
- [Define columns](../Guides/columns-and-sorting.md) and add sorting and filtering.
- [Load data on demand](../Guides/provider-backed-data.md) with `TableDataProvider`.
- [Customize UI and interactions](../Guides/ui-providers-and-customization.md).

Core is configured for Android, iOS, Windows, macOS, and WebAssembly targets.
