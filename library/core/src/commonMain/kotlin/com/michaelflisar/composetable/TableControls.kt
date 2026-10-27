package com.michaelflisar.composetable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaelflisar.composetable.implementation.DropdownMenuItem
import com.michaelflisar.composetable.implementation.HorizontalDivider
import com.michaelflisar.composetable.implementation.Icon
import com.michaelflisar.composetable.implementation.IconButton
import com.michaelflisar.composetable.implementation.OutlinedTextField
import com.michaelflisar.composetable.implementation.TableIcon
import com.michaelflisar.composetable.implementation.TableTextStyle
import com.michaelflisar.composetable.implementation.TableUI
import com.michaelflisar.composetable.implementation.Text
import com.michaelflisar.composetable.implementation.TextButton

/** All built-in text is replaceable; column titles and choice labels belong to column definitions. */
@Stable
data class TableTexts(
    val search: String,
    val filter: String,
    val resetFilters: String,
    val resetSort: String,
    val noData: String,
    val noMatches: String,
    val retry: String,
    val done: String,
    val all: String,
    val selectAll: String,
    val deselectAll: String,
    val checked: String,
    val unchecked: String,
    val caseSensitive: String,
    val invalidNumber: String,
    val rowsPerPage: String,
    val previousPage: String,
    val nextPage: String,
    val firstPage: String,
    val lastPage: String,
    val ascending: String,
    val descending: String,
    val clear: String,
    val selectedCount: (Int) -> String,
    val itemCount: (Int?, Int?) -> String,
    val pageRange: (Int, Int, Int) -> String,
    val operatorLabel: (TableFilterOperator) -> String,
    val sortDescription: (TableSortDirection, Int) -> String,
    val errorMessage: (Throwable) -> String,
) {
    companion object {
        val English = TableTexts(
            search = "Search",
            filter = "Filter",
            resetFilters = "Reset filters",
            resetSort = "Reset sorting",
            noData = "No data",
            noMatches = "No matching rows",
            retry = "Retry",
            done = "Done",
            all = "All",
            selectAll = "Select all",
            deselectAll = "Deselect all",
            checked = "Checked",
            unchecked = "Unchecked",
            caseSensitive = "Case sensitive",
            invalidNumber = "Invalid number",
            rowsPerPage = "Rows per page",
            previousPage = "Previous page",
            nextPage = "Next page",
            firstPage = "First page",
            lastPage = "Last page",
            ascending = "Ascending",
            descending = "Descending",
            clear = "Clear",
            selectedCount = { "$it selected" },
            itemCount = { filtered, total ->
                if (filtered == null) "\u2026" else if (total != null && total != filtered) "$filtered / $total" else "$filtered"
            },
            pageRange = { first, last, total -> "$first\u2013$last / $total" },
            errorMessage = { it.message ?: it.toString() },
            operatorLabel = { operator ->
                when (operator) {
                    TableFilterOperator.Equal -> "Equal"
                    TableFilterOperator.NotEqual -> "Not equal"
                    TableFilterOperator.GreaterOrEqual -> "Greater than or equal"
                    TableFilterOperator.Greater -> "Greater than"
                    TableFilterOperator.LessOrEqual -> "Less than or equal"
                    TableFilterOperator.Less -> "Less than"
                    TableFilterOperator.StartsWith -> "Starts with"
                    TableFilterOperator.EndsWith -> "Ends with"
                    TableFilterOperator.Contains -> "Contains"
                    TableFilterOperator.NotContains -> "Does not contain"
                }
            },
            sortDescription = { direction, priority ->
                "${if (direction == TableSortDirection.Ascending) "Ascending" else "Descending"}, priority $priority"
            }
        )

        val German = TableTexts(
            search = "Suchen",
            filter = "Filter",
            resetFilters = "Filter zurücksetzen",
            resetSort = "Sortierung zurücksetzen",
            noData = "Keine Daten",
            noMatches = "Keine passenden Einträge",
            retry = "Erneut versuchen",
            done = "Fertig",
            all = "Alle",
            selectAll = "Alle auswählen",
            deselectAll = "Alle abwählen",
            checked = "Aktiviert",
            unchecked = "Nicht aktiviert",
            caseSensitive = "Groß-/Kleinschreibung beachten",
            invalidNumber = "Ungültige Zahl",
            rowsPerPage = "Zeilen pro Seite",
            previousPage = "Vorherige Seite",
            nextPage = "Nächste Seite",
            firstPage = "Erste Seite",
            lastPage = "Letzte Seite",
            ascending = "Aufsteigend",
            descending = "Absteigend",
            clear = "Löschen",
            selectedCount = { "$it ausgewählt" },
            itemCount = { filtered, total ->
                if (filtered == null) "\u2026" else if (total != null && total != filtered) "$filtered / $total" else "$filtered"
            },
            pageRange = { first, last, total -> "$first\u2013$last / $total" },
            errorMessage = { it.message ?: it.toString() },
            operatorLabel = { operator ->
                when (operator) {
                    TableFilterOperator.Equal -> "Gleich"
                    TableFilterOperator.NotEqual -> "Ungleich"
                    TableFilterOperator.GreaterOrEqual -> "Größer oder gleich"
                    TableFilterOperator.Greater -> "Größer als"
                    TableFilterOperator.LessOrEqual -> "Kleiner oder gleich"
                    TableFilterOperator.Less -> "Kleiner als"
                    TableFilterOperator.StartsWith -> "Beginnt mit"
                    TableFilterOperator.EndsWith -> "Endet mit"
                    TableFilterOperator.Contains -> "Enthält"
                    TableFilterOperator.NotContains -> "Enthält nicht"
                }
            },
            sortDescription = { direction, priority ->
                "${if (direction == TableSortDirection.Ascending) "Aufsteigend" else "Absteigend"}, Priorität $priority"
            }
        )
    }
}

@Composable
fun <Item> TableScope<Item>.TableToolbar(
    modifier: Modifier = Modifier,
    containerColor: androidx.compose.ui.graphics.Color = TableDefaults.colors().header,
    contentColor: androidx.compose.ui.graphics.Color = TableDefaults.colors().headerContent,
    content: @Composable RowScope.() -> Unit,
) {
    TableUI.provider.Surface(color = containerColor, contentColor = contentColor) {
        Row(
            modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}

@Composable
fun <Item> TableScope<Item>.TableResetSortButton() {
    TableUI.provider.TextButton(
        onClick = { state.reset(sorts = true) },
        enabled = state.query.sorts.isNotEmpty()
    ) { TableUI.provider.Text(texts.resetSort) }
}

/** Visibility affects controls only, never the active query or column capabilities. */
@Composable
fun <Item> TableScope<Item>.TableSearchBar(
    modifier: Modifier = Modifier,
    showTextSearch: Boolean = true,
    showColumnFilters: Boolean = true,
    filterPresentation: TableFilterPresentation = TableFilterPresentation.Dialog,
) {
    if (!showTextSearch && !showColumnFilters) {
        TableUI.provider.HorizontalDivider(modifier.fillMaxWidth())
        return
    }

    Column(
        modifier.fillMaxWidth().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (showTextSearch) TableUI.provider.OutlinedTextField(
            value = state.query.search,
            onValueChange = state::search,
            label = { TableUI.provider.Text(texts.search) },
            leadingIcon = { TableUI.provider.Icon(TableIcon.Search, null) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (showColumnFilters) {
            FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                table.columns.forEachIndexed { columnIndex, column ->
                    if (column.filter == null) return@forEachIndexed
                    val current = state.query.filters[column.id]
                    var open by remember(column.id) { mutableStateOf(false) }
                    Box {
                        TableUI.provider.InputChip(
                            selected = current != null,
                            onClick = { open = true },
                            label = {
                                TableUI.provider.Text(column.title + (current?.let {
                                    ": ${
                                        filterSummary(
                                            column.filter,
                                            it,
                                            texts
                                        )
                                    }"
                                } ?: ""))
                            },
                            trailingIcon = if (current != null) {
                                {
                                    TableUI.provider.IconButton(
                                        onClick = { state.setFilter(column.id, null) },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        TableUI.provider.Icon(
                                            TableIcon.Clear,
                                            texts.clear,
                                            Modifier.size(16.dp)
                                        )
                                    }
                                }
                            } else null
                        )
                        if (open) TableHeaderContext(
                            table,
                            column,
                            columnIndex,
                            texts
                        ).FilterEditor(filterPresentation) { open = false }
                    }
                }
                if (state.query.search.isNotEmpty() || state.query.filters.isNotEmpty()) {
                    TableUI.provider.TextButton(onClick = { state.reset(search = true, filters = true) }) {
                        TableUI.provider.Text(
                            texts.resetFilters
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun <Item> TableHeaderContext<Item>.Label() {
    TableUI.provider.Text(column.title, maxLines = 1)
}

@Composable
fun <Item> TableHeaderContext<Item>.SortIndicator() {
    val index = state.query.sorts.indexOfFirst { it.columnId == column.id }
    if (index >= 0) {
        val sort = state.query.sorts[index]
        TableUI.provider.Icon(
            if (sort.direction == TableSortDirection.Ascending) {
                TableIcon.ArrowUpward
            } else {
                TableIcon.ArrowDownward
            },
            texts.sortDescription(sort.direction, index + 1), Modifier.size(16.dp)
        )
        if (state.query.sorts.size > 1) TableUI.provider.Text(
            "${index + 1}",
            style = TableUI.provider.textStyle(TableTextStyle.LabelSmall)
        )
    }
}

@Composable
fun <Item> TableHeaderContext<Item>.FilterButton() {
    if (showFilter && showFilterIcon && column.filter != null) {
        var open by remember { mutableStateOf(false) }
        Box {
            TableUI.provider.IconButton(onClick = { open = true }, modifier = Modifier.size(32.dp)) {
                TableUI.provider.Icon(
                    TableIcon.FilterAlt,
                    "${texts.filter}: ${column.title}",
                    Modifier.size(20.dp),
                    tint = if (column.id in state.query.filters) {
                        TableUI.provider.primaryColor()
                    } else {
                        TableUI.provider.contentColor()
                    }
                )
            }
            if (open) FilterEditor(filterPresentation) { open = false }
        }
    }
}

@Composable
internal fun <Item> TableHeaderContext<Item>.DefaultHeaderContent() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(Modifier.weight(1f), contentAlignment = column.headerContentAlignment) { Label() }
        SortIndicator()
        FilterButton()
    }
}

@Composable
internal fun <Item> TableHeaderContext<Item>.HeaderMenu(onDismiss: () -> Unit) {
    var filterOpen by remember { mutableStateOf(false) }
    if (filterOpen) {
        FilterEditor(filterPresentation, onDismiss)
    } else TableUI.provider.DropdownMenu(expanded = true, onDismissRequest = onDismiss) {
        if (column.sortable) {
            val direction = state.query.sorts.find { it.columnId == column.id }?.direction
            TableUI.provider.DropdownMenuItem(
                text = { TableUI.provider.Text(texts.ascending) },
                leadingIcon = { TableUI.provider.Icon(TableIcon.ArrowUpward, contentDescription = null) },
                trailingIcon = if (direction == TableSortDirection.Ascending) {
                    { TableUI.provider.Icon(TableIcon.Check, contentDescription = null) }
                } else null,
                onClick = {
                    state.setSort(column.id, TableSortDirection.Ascending); onDismiss()
                }
            )
            TableUI.provider.DropdownMenuItem(
                text = { TableUI.provider.Text(texts.descending) },
                leadingIcon = { TableUI.provider.Icon(TableIcon.ArrowDownward, contentDescription = null) },
                trailingIcon = if (direction == TableSortDirection.Descending) {
                    { TableUI.provider.Icon(TableIcon.Check, contentDescription = null) }
                } else null,
                onClick = {
                    state.setSort(column.id, TableSortDirection.Descending); onDismiss()
                }
            )
            TableUI.provider.DropdownMenuItem(
                text = { TableUI.provider.Text(texts.resetSort) },
                leadingIcon = { TableUI.provider.Icon(TableIcon.Clear, contentDescription = null) },
                onClick = {
                    state.setSort(column.id, null); onDismiss()
                }
            )
        }
        if (showFilter && column.filter != null) {
            TableUI.provider.DropdownMenuItem(
                text = { TableUI.provider.Text(texts.filter) },
                leadingIcon = { TableUI.provider.Icon(TableIcon.FilterAlt, contentDescription = null) },
                onClick = { filterOpen = true }
            )
        }
    }
}

@Composable
internal fun <Item> TableHeaderContext<Item>.FilterEditor(
    presentation: TableFilterPresentation,
    onDismiss: () -> Unit,
) {
    val capability = requireNotNull(column.filter)
    val current = state.query.filters[column.id]
    val draft = if (presentation == TableFilterPresentation.Dialog) {
        remember(column.id, capability) { TableFilterDraft(capability, current) }
    } else {
        remember(column.id, capability, current) { TableFilterDraft(capability, current) }
    }

    fun change(value: TableFilterValue?) {
        draft.edit(value, presentation) { state.setFilter(column.id, it) }
    }

    val editor: @Composable () -> Unit = {
        Column(
            Modifier.heightIn(max = 400.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterFields(capability, draft.value, draft.valid, ::change)
        }
    }
    val clear: @Composable () -> Unit = {
        TableUI.provider.TextButton(onClick = { change(null) }, enabled = draft.canClear) {
            TableUI.provider.Text(texts.clear)
        }
    }
    val done: @Composable () -> Unit = {
        TableUI.provider.TextButton(
            enabled = draft.valid,
            onClick = {
                draft.commit { state.setFilter(column.id, it) }
                onDismiss()
            }
        ) { TableUI.provider.Text(texts.done) }
    }
    if (presentation == TableFilterPresentation.Dialog) TableUI.provider.AlertDialog(
        onDismissRequest = onDismiss,
        title = { TableUI.provider.Text("${texts.filter}: ${column.title}") },
        confirmButton = done,
        dismissButton = clear,
        text = editor
    ) else TableUI.provider.DropdownMenu(expanded = true, onDismissRequest = onDismiss) {
        Column(
            Modifier.widthIn(min = 240.dp, max = 360.dp).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TableUI.provider.Text(
                "${texts.filter}: ${column.title}",
                style = TableUI.provider.textStyle(TableTextStyle.TitleMedium)
            )
            editor()
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                clear()
            }
        }
    }
}

@Composable
private fun <Item> TableHeaderContext<Item>.FilterFields(
    capability: TableFilter<*>,
    current: TableFilterValue?,
    valid: Boolean,
    onChange: (TableFilterValue?) -> Unit,
) {
    when (capability) {
        is TableFilter.Text -> {
            val filter = current as? TableFilterValue.Text ?: TableFilterValue.Text("")
            ChoiceMenu(
                options = textOperators.toList(),
                selected = filter.operator,
                label = texts.operatorLabel
            ) { onChange(filter.copy(operator = it)) }
            TableUI.provider.OutlinedTextField(filter.text, onValueChange = {
                onChange(filter.copy(text = it))
            }, label = { TableUI.provider.Text(texts.filter) }, singleLine = true)
            Row(verticalAlignment = Alignment.CenterVertically) {
                TableUI.provider.Checkbox(
                    !filter.ignoreCase,
                    onCheckedChange = { onChange(filter.copy(ignoreCase = !it)) })
                TableUI.provider.Text(texts.caseSensitive)
            }
        }

        is TableFilter.Number -> {
            val filter = current as? TableFilterValue.Number ?: TableFilterValue.Number("")
            ChoiceMenu(TableFilterOperator.entries, filter.operator, texts.operatorLabel) {
                onChange(filter.copy(operator = it))
            }
            TableUI.provider.OutlinedTextField(
                filter.text, onValueChange = {
                    onChange(filter.copy(text = it))
                }, label = { TableUI.provider.Text(texts.filter) }, isError = !valid, singleLine = true,
                supportingText = { if (!valid) TableUI.provider.Text(texts.invalidNumber) }
            )
        }

        is TableFilter.Choice -> {
            val selected = (current as? TableFilterValue.Choices)?.keys.orEmpty()
            val labels = capability.labels()
            if (capability.multiSelect) {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    TableUI.provider.TextButton(
                        onClick = { onChange(TableFilterValue.Choices(labels.keys)) },
                        enabled = labels.keys.any { it !in selected }
                    ) {
                        TableUI.provider.Icon(
                            TableIcon.SelectAll,
                            contentDescription = null,
                            modifier = Modifier.size(TableUI.provider.buttonIconSize())
                        )
                        Spacer(Modifier.width(TableUI.provider.buttonIconSpacing()))
                        TableUI.provider.Text(texts.selectAll)
                    }
                    TableUI.provider.TextButton(onClick = { onChange(null) }, enabled = selected.isNotEmpty()) {
                        TableUI.provider.Icon(
                            TableIcon.Deselect,
                            contentDescription = null,
                            modifier = Modifier.size(TableUI.provider.buttonIconSize())
                        )
                        Spacer(Modifier.width(TableUI.provider.buttonIconSpacing()))
                        TableUI.provider.Text(texts.deselectAll)
                    }
                }
            } else {
                TableUI.provider.TextButton(onClick = { onChange(null) }, enabled = selected.isNotEmpty()) {
                    TableUI.provider.Text(
                        texts.all
                    )
                }
            }
            labels.forEach { (key, label) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val change: () -> Unit = {
                        val keys = if (capability.multiSelect) {
                            if (key in selected) selected - key else selected + key
                        } else setOf(key)
                        onChange(TableFilterValue.Choices(keys))
                    }
                    if (capability.multiSelect) TableUI.provider.Checkbox(
                        key in selected,
                        onCheckedChange = { change() })
                    else TableUI.provider.RadioButton(key in selected, onClick = change)
                    TableUI.provider.TextButton(onClick = change) {
                        TableUI.provider.Text(label)
                    }
                }
            }
        }

        is TableFilter.BooleanFilter -> {
            val selected = (current as? TableFilterValue.BooleanValue)?.value
            listOf(
                null to texts.all,
                true to texts.checked,
                false to texts.unchecked
            ).forEach { (value, label) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TableUI.provider.RadioButton(selected == value, onClick = {
                        onChange(value?.let { TableFilterValue.BooleanValue(it) })
                    })
                    TableUI.provider.Text(label)
                }
            }
        }

        is TableFilter.Custom -> {
            val editorScope =
                TableCustomFilterScope(current as? TableFilterValue.Custom, texts) { fields ->
                    onChange(fields?.let { TableFilterValue.Custom(capability.type, it) })
                }
            capability.content(editorScope)
        }
    }
}

@Composable
internal fun <Value> ChoiceMenu(
    options: List<Value>,
    selected: Value,
    label: (Value) -> String,
    onSelect: (Value) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        TableUI.provider.OutlinedButton(onClick = { expanded = true }) {
            TableUI.provider.Text(label(selected))
        }
        TableUI.provider.DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                TableUI.provider.DropdownMenuItem(
                    text = { TableUI.provider.Text(label(option)) },
                    onClick = { onSelect(option); expanded = false })
            }
        }
    }
}

private fun filterSummary(
    filter: TableFilter<*>?,
    value: TableFilterValue,
    texts: TableTexts,
): String = when (value) {
    is TableFilterValue.Text -> "${texts.operatorLabel(value.operator)} ${value.text}"
    is TableFilterValue.Number -> "${texts.operatorLabel(value.operator)} ${value.text}"
    is TableFilterValue.BooleanValue -> if (value.value) texts.checked else texts.unchecked
    is TableFilterValue.Choices -> {
        val labels = (filter as? TableFilter.Choice)?.labels().orEmpty()
        value.keys.joinToString { labels[it] ?: it }
    }

    is TableFilterValue.Custom -> (filter as? TableFilter.Custom)?.summary?.invoke(value.fields)
        .orEmpty()
}

@Composable
fun <Item> TableScope<Item>.TableFooter(
    modifier: Modifier = Modifier,
    containerColor: androidx.compose.ui.graphics.Color = TableDefaults.colors().header,
    contentColor: androidx.compose.ui.graphics.Color = TableDefaults.colors().headerContent,
    content: @Composable RowScope.() -> Unit = {
        TableItemCount()
        if (state.selectedKeys.isNotEmpty()) {
            Spacer(Modifier.width(8.dp)); TableSelectionCount()
        }
        Spacer(Modifier.weight(1f))
        TablePagination()
    },
) = TableToolbar(modifier, containerColor, contentColor, content)

@Composable
fun <Item> TableScope<Item>.TableItemCount() {
    TableUI.provider.Text(texts.itemCount(table.totalCount, (table as? LocalTableModel)?.items?.size))
}

@Composable
fun <Item> TableScope<Item>.TableSelectionCount() {
    TableUI.provider.Text(texts.selectedCount(state.selectedKeys.size))
}

@Composable
fun <Item> TableScope<Item>.TablePagination(modifier: Modifier = Modifier) {
    val display = state.display as? TableDisplay.Paged ?: return
    val total = table.totalCount ?: return
    FlowRow(modifier, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        TableUI.provider.Text(texts.rowsPerPage, Modifier.align(Alignment.CenterVertically))
        ChoiceMenu(display.pageSizes, display.pageSize, { "$it" }) {
            state.setDisplay(
                display.copy(
                    pageSize = it
                )
            )
        }
        TableUI.provider.Text(
            texts.pageRange(
                if (total == 0) 0 else table.firstVisibleIndex + 1,
                table.firstVisibleIndex + table.visibleCount,
                total
            ),
            Modifier.align(Alignment.CenterVertically)
        )
        TableUI.provider.IconButton(onClick = { table.goToPage(0) }, enabled = table.currentPage > 0) {
            TableUI.provider.Icon(TableIcon.FirstPage, texts.firstPage)
        }
        TableUI.provider.IconButton(
            onClick = { table.goToPage(table.currentPage - 1) },
            enabled = table.currentPage > 0
        ) {
            TableUI.provider.Icon(TableIcon.ChevronLeft, texts.previousPage)
        }
        TableUI.provider.IconButton(
            onClick = { table.goToPage(table.currentPage + 1) },
            enabled = table.currentPage + 1 < table.pageCount
        ) {
            TableUI.provider.Icon(TableIcon.ChevronRight, texts.nextPage)
        }
        TableUI.provider.IconButton(
            onClick = { table.goToPage(table.pageCount - 1) },
            enabled = table.currentPage + 1 < table.pageCount
        ) {
            TableUI.provider.Icon(TableIcon.LastPage, texts.lastPage)
        }
    }
}

@Composable
fun <Item> TableScope<Item>.TableEmptyContent() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val filtered = state.query.search.isNotEmpty() || state.query.filters.isNotEmpty()
        TableUI.provider.Text(if (filtered) texts.noMatches else texts.noData)
        if (filtered) TableUI.provider.TextButton(onClick = { state.reset(search = true, filters = true) }) {
            TableUI.provider.Text(
                texts.resetFilters
            )
        }
    }
}

@Composable
fun <Item> TableScope<Item>.TableErrorContent(error: TableError) {
    Column {
        TableUI.provider.Text(texts.errorMessage(error.error), color = TableUI.provider.errorColor())
        TableUI.provider.TextButton(onClick = error::retry) {
            TableUI.provider.Icon(
                TableIcon.Refresh,
                contentDescription = null,
                modifier = Modifier.size(TableUI.provider.buttonIconSize())
            )
            Spacer(Modifier.width(TableUI.provider.buttonIconSpacing()))
            TableUI.provider.Text(texts.retry)
        }
    }
}
