# Getting Started

This guide builds a small table from an in-memory list. Add the `material3` or
`styled` implementation artifact; the published repository and dependency
setup are shown in the root README's Setup section. The selected artifact
provides its table implementation automatically. When using Material 3, render
the table under `MaterialTheme`; when using Styled, configure the desired
ComposeStyled renderer.

## Define columns and render the table

<!-- snippet: GettingStartedTable -->
```kt
private data class Person(val id: String, val name: String, val age: Int)

@Composable
private fun PeopleTable(people: List<Person>) {
    val columns = remember {
        listOf<TableColumn<Person, *>>(
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
            ),
        )
    }
    val table = rememberTable(
        initialItems = { people },
        columns = columns,
        itemKey = { it.id },
    )

    Table(table = table, modifier = Modifier.fillMaxSize())
}
```
<!-- endSnippet -->

`id` is the stable identity for a row and must be unique. Column IDs must also
be unique and non-blank. `initialItems` is evaluated when the remembered model
is created; later changes to the `people` parameter do not replace its data.
Update a model with its suspend mutation methods, such as `replaceAll`, or use a
new `TableState` when switching to an independent data source.

`Table` contains a lazy row list and therefore needs a bounded height. In a
screen `Column`, for example, give it `Modifier.weight(1f).fillMaxWidth()`; in
a full-screen layout, `Modifier.fillMaxSize()` is suitable.

For remote or database-backed data, use the provider-based `rememberTable`
overload instead. See [Provider-backed Data](provider-backed-data.md).
