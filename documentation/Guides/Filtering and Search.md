ComposeTable keeps filter definitions on columns and active filter values in
`TableState.query`. Global text search checks each column's `searchText`
projection; column filters are applied together with search.

## Add filters

Use the typed filter implementations that match the column value:

- `textFilter()` or `TableFilter.Text` supports equality and text matching
  operators, including contains, starts-with, and ends-with.
- `numberFilter(parse)` or `TableFilter.Number` parses the filter input to the
  column's comparable value type before numeric comparisons.
- `TableFilter.Choice` filters by stable option keys and supports single or
  multiple selection.
- `TableFilter.BooleanFilter` filters Boolean values.
- `TableFilter.Custom` lets an application define structured filter data,
  validation, matching, summary text, and its own composable editor.

Use `searchText = null` to exclude a column from global text search, or supply a
projection for formatted values. The default projections for common column
factories use the value's text representation.

## Show filter controls

`TableSearchBar` provides global search and a row of filters for filterable
columns. Header filter buttons are enabled by default. `showHeaderFilters`
controls their visibility but does not clear active filters. The toolbar's
`showColumnFilters` parameter controls its filter chips independently.

`TableFilterPresentation.Dialog` edits a draft and applies valid changes when
the user confirms; dismissing the dialog discards the draft. `Popup` applies
valid edits immediately. Set the presentation with `TableSearchBar`'s
`filterPresentation` or `Table`'s `headerFilterPresentation` parameter.

Changing the query resets the current page and scroll position. Reset only the
desired parts of the query with `state.reset(search = true, filters = true)`;
the default empty-content UI offers a reset action when search or filters hide
all rows.

For provider-backed tables, the provider—not the UI—must apply all search,
filter, and sorting semantics to the complete data source. See
[Provider-backed Data](Provider-backed%20Data.md).
