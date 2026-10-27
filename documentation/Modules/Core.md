ComposeTable Core provides the table composable, data models, typed columns,
query and selection state, and the UI-provider contract. It is the base module
for every ComposeTable table and does not depend on Material 3.

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

- [Get started](../Guides/Getting%20Started.md) with a local table.
- [Define columns](../Guides/Columns%20and%20Sorting.md) and add sorting and filtering.
- [Load data on demand](../Guides/Provider-backed%20Data.md) with `TableDataProvider`.
- [Customize UI and interactions](../Guides/UI%20Providers%20and%20Customization.md).

Core is configured for Android, iOS, Windows, macOS, and WebAssembly targets.
