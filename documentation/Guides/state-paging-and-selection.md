# State, Paging, and Selection

`TableState` holds the active query, display mode, page, and selected item keys.
See [Filtering and Search](filtering-and-search.md) for query state details.
Create one state per table with `rememberTableState()` and pass it to
`rememberTable` when you need explicit control or want to preserve the table's
interaction state.

## Continuous and paged display

The default `TableDisplay.Continuous` shows all matching rows in a lazy list.
`TableDisplay.Paged(pageSize, pageSizes)` limits the visible result to a page
and enables `TablePagination()` in the default footer. The selected `pageSize`
must be positive and included in `pageSizes`.

The table and state own page changes. Use `table.goToPage(index)` to navigate
programmatically or `state.setDisplay(...)` to change display mode or page
size. Search, filters, sorting, and display changes reset the position as
needed. `table.totalCount` is the local filtered result count or the provider's
asynchronously loaded count.

## Select rows

Set `interaction = TableInteraction.Select()` on `Table` to make rows toggle
selection when clicked. Selection is keyed by the stable `itemKey` supplied to
`rememberTable`; read it through `state.selectedKeys` and change it with
`state.select(key, selected)` or `state.toggleSelection(key)`. Selection is
independent of the current page and query. Removing an item clears its key;
replacing all items retains selection only for keys that still exist.

Provider models can select rows that are not currently cached. Use
`selectedKeys` for the complete selection and `loadedSelectedItems` only when
you specifically need selected objects currently available in the cache.

`TableInteraction.RowClick` and `TableInteraction.CellClick` provide alternate
click handling. These interaction modes are mutually exclusive: choose one
mode for the table.

## Reset state

`state.reset()` clears query, selection, and position while keeping display
settings. Pass flags such as `state.reset(filters = true, sorts = true)` to
clear only selected parts. Any query change resets the current page and
scroll position.
