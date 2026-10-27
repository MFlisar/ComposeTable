Columns describe how an item becomes a table cell. Each column has a stable,
unique `id`, a display `title`, a value selector, and optional width, sorting,
search, filtering, and rendering behavior.

## Use a column factory

The factories provide useful defaults for common value types:

```kotlin
val columns = listOf<TableColumn<Person, *>>(
    textColumn(
        id = "name",
        title = "Name",
        value = { it.name },
        filter = textFilter(),
    ),
    numberColumn(
        id = "age",
        title = "Age",
        value = { it.age },
        filter = numberFilter(String::toIntOrNull),
    ),
)
```

`textColumn`, `numberColumn`, `enumColumn`, and `booleanColumn` include default
cell rendering and natural ordering. `customColumn` is for other types,
formatted or interactive cells, and action columns. Custom columns are not
searchable or sortable unless `searchText` and `comparator` are supplied.
Override `cellContent` or `headerContent` to render custom composable content;
call the context's `Content()` to delegate to its default renderer.

## Set column widths

`ColumnWidth.Weight()` is the default and distributes available table width.
Use `ColumnWidth.Fixed(width)` for a fixed width or `ColumnWidth.Auto()` to
measure content up to the configured limits. Auto sizing measures every local
row, but only rows currently cached by a provider-backed model; it does not
load additional data.

`TableColumnDefaults` supplies the default width, cell padding, and independent
header and cell alignments. Override these per column when a particular value
needs different spacing or alignment.

## Configure sorting

Columns are sortable when they have a comparator. The text, number, enum, and
boolean factories provide a default comparator; a custom column is not
sortable unless you pass one. By default, clicking a sortable header cycles
through ascending, descending, and unsorted. Set sort priorities explicitly
with `table.state.setSort(columnId, direction)`; the order of `TableSort`
entries determines multi-column sort priority.

For local data, the model applies sorting and filtering to the full list before
display or paging. A provider must apply the complete `TableQuery` consistently
across its count and load operations. See [Filtering and Search](Filtering%20and%20Search.md)
and [Provider-backed Data](Provider-backed%20Data.md).
