# ComposeTable Core Sources

ComposeTable Core contains the shared table composable, data models, typed
columns, and query and selection state. It is an internal source module; add a
UI implementation such as `material3` or `styled` to an application instead.

## Choose a data source

Create a local model with `rememberTable(initialItems = ...)` for data already
available in memory. Use the `TableDataProvider` overload when data should be
counted and loaded on demand, such as a database or remote service. Both models
use the same `TableModel`, `TableColumn`, `TableState`, and `Table` APIs.

## Next steps

- [Get started](../Guides/getting-started.md) with a local table.
- [Define columns](../Guides/columns-and-sorting.md) and add sorting and filtering.
- [Load data on demand](../Guides/provider-backed-data.md) with `TableDataProvider`.
- [Customize tables](../Guides/table-customization.md).

The shared sources support Android, iOS, Windows, macOS, and WebAssembly.
