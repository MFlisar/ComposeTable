package com.michaelflisar.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.michaelflisar.composetable.ColumnWidth
import com.michaelflisar.composetable.ProviderTableModel
import com.michaelflisar.composetable.Table
import com.michaelflisar.composetable.TableCache
import com.michaelflisar.composetable.TableColumn
import com.michaelflisar.composetable.TableDataProvider
import com.michaelflisar.composetable.TableDisplay
import com.michaelflisar.composetable.TableEmptyContent
import com.michaelflisar.composetable.TableErrorContent
import com.michaelflisar.composetable.TableFilter
import com.michaelflisar.composetable.TableFilterPresentation
import com.michaelflisar.composetable.TableFooter
import com.michaelflisar.composetable.TableHeaderCell
import com.michaelflisar.composetable.TableHeaderInteraction
import com.michaelflisar.composetable.TableInteraction
import com.michaelflisar.composetable.TableItemCount
import com.michaelflisar.composetable.TableLazyConfig
import com.michaelflisar.composetable.TableModel
import com.michaelflisar.composetable.TablePagination
import com.michaelflisar.composetable.TableQuery
import com.michaelflisar.composetable.TableResetSortButton
import com.michaelflisar.composetable.TableSearchBar
import com.michaelflisar.composetable.TableSelectionCount
import com.michaelflisar.composetable.TableState
import com.michaelflisar.composetable.TableTexts
import com.michaelflisar.composetable.TableToolbar
import com.michaelflisar.composetable.implementation.TableUI
import com.michaelflisar.composetable.applyTableQuery
import com.michaelflisar.composetable.booleanColumn
import com.michaelflisar.composetable.customColumn
import com.michaelflisar.composetable.enumColumn
import com.michaelflisar.composetable.material3.Material3TableUIProvider
import com.michaelflisar.composetable.numberColumn
import com.michaelflisar.composetable.numberFilter
import com.michaelflisar.composetable.rememberTable
import com.michaelflisar.composetable.rememberTableState
import com.michaelflisar.composetable.textColumn
import com.michaelflisar.composetable.textFilter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

// begin-snippet: GettingStartedTable
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
// end-snippet

@Composable
fun DemoContent() {

    // once in the app: register implementation
    // begin-snippet: InstallMaterial3Provider
    TableUI.set(Material3TableUIProvider)
    // end-snippet

    MaterialTheme {

        val snackbarHostState = remember { SnackbarHostState() }
        val snackbarScope = rememberCoroutineScope()
        val showSnackbar: (String, String) -> Unit = { title, message ->
            snackbarScope.launch {
                snackbarHostState.showSnackbar("$title: $message")
            }
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            val providerMode = remember { mutableStateOf(false) }
            key(providerMode.value) {
                Table(paddingValues, providerMode, showSnackbar)
            }
        }
    }
}

@Composable
private fun Table(
    paddingValues: PaddingValues,
    providerMode: MutableState<Boolean>,
    showSnackbar: (String, String) -> Unit,
) {
    // 1) setting states
    val autoWidth = remember { mutableStateOf(false) }
    val headerInteractionMode = remember { mutableStateOf(1) }
    val interactionMode = remember { mutableStateOf(0) }
    val failNextCount = remember { mutableStateOf(false) }
    val failNextLoad = remember { mutableStateOf(false) }
    val showTextSearch = remember { mutableStateOf(true) }
    val showToolbarFilters = remember { mutableStateOf(true) }
    val showHeaderFilters = remember { mutableStateOf(true) }
    val toolbarFilterPresentation = remember { mutableStateOf(TableFilterPresentation.Dialog) }
    val headerFilterPresentation = remember { mutableStateOf(TableFilterPresentation.Dialog) }
    val customEmpty = remember { mutableStateOf(false) }
    val customErrors = remember { mutableStateOf(false) }

    // 2) table states

    val state = rememberTableState(TableDisplay.Paged())
    val columns = table2Columns(autoWidth.value, showSnackbar)

    val table: TableModel<TableItem> = if (providerMode.value) {
        val currentColumns = rememberUpdatedState(columns)
        val provider = remember {
            val generation = ++table2Generation
            DemoTableProvider(
                size = 1000,
                createItems = { start, count -> table2Items(start, count, generation) },
                failNextCount = failNextCount,
                failNextLoad = failNextLoad
            ) { currentColumns.value }
        }
        rememberTable(
            provider = provider,
            columns = columns,
            state = state,
            lazyConfig = TableLazyConfig(cache = TableCache.Lru(5))
        ) { it.id }
    } else {
        rememberTable(
            initialItems = { table2Items(0, 1000) },
            columns = columns,
            state = state
        ) { it.id }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // ----------------------
        // content above the table
        // ----------------------

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            SetupRow(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                table = table,
                state = state,
                providerMode = providerMode,
                autoWidth = autoWidth,
                headerInteractionMode = headerInteractionMode,
                interactionMode = interactionMode,
                failNextCount = failNextCount,
                failNextLoad = failNextLoad,
                showTextSearch = showTextSearch,
                showToolbarFilters = showToolbarFilters,
                showHeaderFilters = showHeaderFilters,
                toolbarFilterPresentation = toolbarFilterPresentation,
                headerFilterPresentation = headerFilterPresentation,
                customErrors = customErrors,
                customEmpty = customEmpty,
                showSnackbar = showSnackbar
            )
        }

        // ----------------------
        // table
        // ----------------------

        Table(
            table = table,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            interaction = when (interactionMode.value) {
                1 -> TableInteraction.Select()
                2 -> TableInteraction.RowClick {
                    showSnackbar("Row clicked", "Row: ${it.name}")
                }

                3 -> TableInteraction.CellClick { item, column ->
                    showSnackbar(
                        "Cell clicked",
                        "Row: ${item.name}, Column: ${column.title}"
                    )
                }

                else -> TableInteraction.None()
            },
            texts = TableTexts.English,
            showHeaderFilters = showHeaderFilters.value,
            headerFilterPresentation = headerFilterPresentation.value,
            emptyContent = {
                Column {
                    if (customEmpty.value)
                        Text("Provider empty")
                    TableEmptyContent()
                }
            },
            errorContent = { error ->
                Column {
                    if (customErrors.value)
                        Text(
                            "Provider error: ${error.kind}${
                                error.index?.let { " at row $it" }.orEmpty()
                            }"
                        )
                    TableErrorContent(error)
                }
            },
            headerInteraction = when (headerInteractionMode.value) {
                1 -> TableHeaderInteraction.SortOnClick
                2 -> TableHeaderInteraction.MenuOnClick
                3 -> TableHeaderInteraction.Click { column ->
                    showSnackbar("Header clicked", "Column: ${column.title}")
                }

                else -> TableHeaderInteraction.None
            },
            header = {

                TableToolbar {
                    Text(
                        text = "Table 2 - Table title",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.weight(1f))
                    TableResetSortButton()
                }

                TableSearchBar(
                    showTextSearch = showTextSearch.value,
                    showColumnFilters = showToolbarFilters.value,
                    filterPresentation = toolbarFilterPresentation.value
                )
            },
            footer = {
                TableFooter {

                    TableItemCount()
                    Spacer(Modifier.width(8.dp))
                    Text("|")
                    Spacer(Modifier.width(8.dp))
                    TableSelectionCount()

                    Spacer(Modifier.weight(1f))
                    TablePagination()
                }
            },
            headerCell = { cell ->
                if (cell.columnIndex in listOf(5, 6)) {
                    TooltipWrapper(cell)
                } else {
                    cell.Content()
                }
            },
            cell = { it.Content() }
        )

        // ----------------------
        // content below the table
        // ----------------------

        Text(
            "Selected keys: ${
                state.selectedKeys.take(10).joinToString()
            } | Cached selected items: ${table.loadedSelectedItems.size}",
        )
    }
}

@Composable
private fun TooltipWrapper(cell: TableHeaderCell<TableItem>) {
    TooltipBox(
        tooltip = {
            Text(cell.column.title)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            cell.Content()
        }
    }
}

@Composable
private fun SetupRow(
    modifier: Modifier,
    table: TableModel<TableItem>,
    state: TableState,
    providerMode: MutableState<Boolean>,
    autoWidth: MutableState<Boolean>,
    headerInteractionMode: MutableState<Int>,
    interactionMode: MutableState<Int>,
    failNextCount: MutableState<Boolean>,
    failNextLoad: MutableState<Boolean>,
    showTextSearch: MutableState<Boolean>,
    showToolbarFilters: MutableState<Boolean>,
    showHeaderFilters: MutableState<Boolean>,
    toolbarFilterPresentation: MutableState<TableFilterPresentation>,
    headerFilterPresentation: MutableState<TableFilterPresentation>,
    customErrors: MutableState<Boolean>,
    customEmpty: MutableState<Boolean>,
    showSnackbar: (String, String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var replacing by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(true) }
    var actionsExpanded by remember { mutableStateOf(false) }

    fun replaceData(empty: Boolean) {
        scope.launch {
            replacing = true
            try {
                if (empty) table.clear() else table.replaceAll(table2Items(0, 1000))
            } catch (error: CancellationException) {
                throw error
            } catch (error: Exception) {
                showSnackbar("Data replacement failed", error.message ?: error.toString())
            } finally {
                replacing = false
            }
        }
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier,//.background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Settings",
                style = MaterialTheme.typography.titleMedium
            )

            Box {
                Button(
                    onClick = { actionsExpanded = true }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Actions")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
                DropdownMenu(
                    expanded = actionsExpanded,
                    onDismissRequest = { actionsExpanded = false },
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(all = 8.dp),
                        text = "Data",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    DropdownMenuItem(
                        text = { Text("New data") },
                        enabled = !replacing,
                        onClick = {
                            actionsExpanded = false
                            replaceData(false)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Empty data") },
                        enabled = !replacing,
                        onClick = {
                            actionsExpanded = false
                            replaceData(true)
                        }
                    )
                    if (table is ProviderTableModel) {
                        DropdownMenuItem(
                            text = { Text("Refresh") },
                            onClick = {
                                actionsExpanded = false
                                table.refresh()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Fail count") },
                            onClick = {
                                actionsExpanded = false
                                failNextCount.value = true
                                table.refresh()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Fail range") },
                            onClick = {
                                actionsExpanded = false
                                failNextLoad.value = true
                                table.refresh()
                            }
                        )
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(all = 8.dp),
                        text = "Reset state",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    DropdownMenuItem(
                        text = { Text("Reset state (all)") },
                        onClick = {
                            actionsExpanded = false
                            state.reset()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Reset search") },
                        onClick = {
                            actionsExpanded = false
                            state.reset(search = true)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Reset filters") },
                        onClick = {
                            actionsExpanded = false
                            state.reset(filters = true)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Reset sorts") },
                        onClick = {
                            actionsExpanded = false
                            state.reset(sorts = true)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Reset selection") },
                        onClick = {
                            actionsExpanded = false
                            state.reset(selection = true)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Reset position") },
                        onClick = {
                            actionsExpanded = false
                            state.reset(position = true)
                        }
                    )
                }
            }

            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse settings" else "Expand settings"
                )
            }
        }

        if (expanded) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                itemVerticalAlignment = Alignment.CenterVertically,
            ) {
                CheckableChip(
                    title = "Suspend provider",
                    checked = providerMode.value,
                    onCheckedChange = { providerMode.value = it }
                )
                CheckableChip(
                    title = "Paged",
                    checked = state.display is TableDisplay.Paged,
                    onCheckedChange = { paged ->
                        state.setDisplay(if (paged) TableDisplay.Paged() else TableDisplay.Continuous)
                    }
                )
                CheckableChip(
                    title = "Auto name width",
                    checked = autoWidth.value,
                    onCheckedChange = { autoWidth.value = it }
                )
                DropdownIndex(
                    title = "Header interaction",
                    items = listOf("None", "Sort on click", "Menu on click", "Header click"),
                    selectedIndex = headerInteractionMode
                )
                DropdownIndex(
                    title = "Cell Click",
                    items = listOf("No clicks", "Select", "Row click", "Cell click"),
                    selectedIndex = interactionMode
                )
            }

            Text("Options", style = MaterialTheme.typography.titleMedium)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                itemVerticalAlignment = Alignment.CenterVertically
            ) {
                CheckableChip("Text search", showTextSearch.value) { showTextSearch.value = it }
                CheckableChip("Toolbar filters", showToolbarFilters.value) {
                    showToolbarFilters.value = it
                }
                CheckableChip("Header filters", showHeaderFilters.value) {
                    showHeaderFilters.value = it
                }
                CheckableChip("Custom errors", customErrors.value) { customErrors.value = it }
                CheckableChip("Custom empty", customEmpty.value) { customEmpty.value = it }
                Dropdown(
                    title = "Toolbar filters",
                    items = TableFilterPresentation.entries,
                    selected = toolbarFilterPresentation.value,
                    onSelectionChange = { _, value -> toolbarFilterPresentation.value = value }
                )
                Dropdown(
                    title = "Header filters",
                    items = TableFilterPresentation.entries,
                    selected = headerFilterPresentation.value,
                    onSelectionChange = { _, value -> headerFilterPresentation.value = value }
                )
            }
        }
    }
}

// ------------------------------
// Klassen und Daten für die Demo
// ------------------------------

private data class TableItem(
    val id: Int,
    val name: String,
    val description: String,
    val type: TableType,
    val checked: MutableState<Boolean>,
)

private enum class TableType { First, Second, Third }

private var table2Generation = 0L

private fun table2Items(
    from: Int,
    size: Int,
    generation: Long = ++table2Generation,
): List<TableItem> {
    require(from >= 0 && size >= 0 && from.toLong() + size <= Int.MAX_VALUE.toLong() + 1)
    return List(size) { offset ->
        val id = from + offset
        TableItem(
            id,
            "Item $id - dataset $generation",
            "Description of item $id from dataset $generation",
            TableType.entries[id % TableType.entries.size],
            mutableStateOf(id % 2 == 0)
        )
    }
}

@Composable
private fun table2Columns(
    autoWidth: Boolean,
    showSnackbar: (String, String) -> Unit,
): List<TableColumn<TableItem, *>> {
    val columns = remember(autoWidth) {
        listOf<TableColumn<TableItem, *>>(
            numberColumn(
                id = "id",
                title = "ID",
                value = { it.id },
                width = ColumnWidth.Fixed(80.dp),
                filter = numberFilter(String::toIntOrNull)
            ),
            textColumn(
                id = "name",
                title = "Name",
                value = { it.name },
                width = if (autoWidth) ColumnWidth.Auto() else ColumnWidth.Weight(minWidth = 160.dp),
                filter = textFilter()
            ),
            textColumn(
                id = "description",
                title = "Description",
                value = { it.description },
                width = ColumnWidth.Weight(minWidth = 200.dp),
                filter = textFilter()
            ),
            enumColumn(
                id = "type",
                title = "Type",
                value = { item: TableItem -> item.type },
                width = ColumnWidth.Fixed(150.dp),
                comparator = compareBy { it.ordinal },
                filter = TableFilter.Choice(TableType.entries, { it.name }, multiSelect = true),
                cellContent = { Text(it.value.name) }
            ),
            booleanColumn(
                id = "checked",
                title = "Checked",
                value = { item: TableItem -> item.checked.value },
                width = ColumnWidth.Fixed(130.dp),
                comparator = naturalOrder(),
                filter = TableFilter.BooleanFilter(),
                searchText = null, //{ if (it) "Checked 1" else "Unchecked 1" },
                cellContent = { cell ->
                    Checkbox(
                        cell.value,
                        onCheckedChange = { cell.item.checked.value = it })
                },
                cellContentAlignment = Alignment.Center,
            ),
            customColumn(
                id = "icon",
                title = "Icon",
                value = { _: TableItem -> Icons.Default.Info },
                width = ColumnWidth.Fixed(64.dp), searchText = null,
                headerContent = { Icon(Icons.Default.Info, "Icon") },
                cellContent = { Icon(it.value, null) },
                headerContentAlignment = Alignment.Center,
                cellContentAlignment = Alignment.Center,
            ),
            customColumn(
                id = "delete",
                title = "Delete",
                value = { item: TableItem -> item.id },
                width = ColumnWidth.Fixed(64.dp), searchText = null,
                headerContent = { Icon(Icons.Default.Delete, "Delete") },
                cellContent = { cell ->
                    val scope = rememberCoroutineScope()
                    IconButton(onClick = {
                        scope.launch {
                            try {
                                cell.table.remove(cell.value)
                            } catch (error: CancellationException) {
                                throw error
                            } catch (error: Exception) {
                                showSnackbar(
                                    "Delete failed",
                                    error.message ?: error.toString()
                                )
                            }
                        }
                    }) {
                        Icon(Icons.Default.Delete, "Delete ${cell.item.name}")
                    }
                },
                headerContentAlignment = Alignment.Center,
                cellContentAlignment = Alignment.Center,
            )
        )
    }
    return columns
}

/** The generator must return stable values for each absolute ID throughout this source lifetime. */
private class DemoTableProvider(
    size: Int,
    private val createItems: (start: Int, count: Int) -> List<TableItem>,
    val failNextCount: MutableState<Boolean>,
    val failNextLoad: MutableState<Boolean>,
    val columns: () -> List<TableColumn<TableItem, *>>,
) : TableDataProvider<TableItem> {

    private var generatedSize = size
    private val removed = mutableSetOf<Int>()
    private val overrides = linkedMapOf<Int, TableItem>()
    private val checkedStates = mutableMapOf<Int, MutableState<Boolean>>()

    init {
        require(size >= 0)
    }

    private fun contains(id: Int) =
        id in overrides || (id in 0 until generatedSize && id !in removed)

    private fun ids(): List<Int> =
        (0 until generatedSize).filter { it !in removed } +
                overrides.keys.filter { it !in 0 until generatedSize }

    private fun itemsFor(ids: List<Int>): List<TableItem> {
        val result = ArrayList<TableItem>(ids.size)
        var position = 0
        while (position < ids.size) {
            val id = ids[position]
            val replacement = overrides[id]
            if (replacement != null) {
                result += replacement
                position++
            } else {
                var end = position + 1
                while (end < ids.size && ids[end] == ids[end - 1] + 1 && ids[end] !in overrides) end++
                val generated = createItems(id, end - position)
                require(generated.size == end - position && generated.indices.all { generated[it].id == ids[position + it] }) {
                    "Generator must return the requested number of items with absolute index IDs"
                }
                result += generated.map { item ->
                    item.copy(checked = checkedStates.getOrPut(item.id) { item.checked })
                }
                position = end
            }
        }
        return result
    }

    override suspend fun insert(item: TableItem) {
        require(!contains(item.id))
        overrides[item.id] = item
        removed.remove(item.id)
    }

    override suspend fun update(key: Any, item: TableItem) {
        require(key == item.id && contains(item.id))
        overrides[item.id] = item
    }

    override suspend fun remove(key: Any) {
        require(key is Int && contains(key))
        overrides.remove(key)
        checkedStates.remove(key)
        if (key in 0 until generatedSize) removed += key
    }

    override suspend fun replaceAll(items: List<TableItem>) {
        require(items.map { it.id }.distinct().size == items.size)
        generatedSize = 0
        removed.clear()
        checkedStates.clear()
        overrides.clear()
        items.forEach { overrides[it.id] = it }
    }

    override suspend fun count(query: TableQuery): Long {
        delay(250.milliseconds)
        if (failNextCount.value) {
            failNextCount.value = false
            error("Simulated count failure")
        }
        val ids = ids()
        return if (query.search.isEmpty() && query.filters.isEmpty()) ids.size.toLong()
        else applyTableQuery(
            itemsFor(ids),
            columns(),
            query.copy(sorts = emptyList())
        ).size.toLong()
    }

    override suspend fun load(query: TableQuery, offset: Long, limit: Int): List<TableItem> {
        delay(350.milliseconds)
        if (failNextLoad.value) {
            failNextLoad.value = false
            error("Simulated range failure")
        }
        require(offset >= 0 && offset <= Int.MAX_VALUE && limit > 0)
        val ids = ids()
        return if (query.search.isEmpty() && query.filters.isEmpty() && query.sorts.isEmpty()) {
            itemsFor(ids.drop(offset.toInt()).take(limit))
        } else {
            // Arbitrary Kotlin filters/comparators require global evaluation in this demo source.
            applyTableQuery(itemsFor(ids), columns(), query).drop(offset.toInt()).take(limit)
        }
    }
}

@Composable
private fun CheckableChip(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    ElevatedFilterChip(
        selected = checked,
        onClick = { onCheckedChange(!checked) },
        label = { Text(title) },
        leadingIcon = if (checked) {
            {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TooltipBox(
    tooltip: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
            TooltipAnchorPosition.Above
        ),
        tooltip = {
            PlainTooltip(content = tooltip)
        },
        state = rememberTooltipState(),
        content = content,
    )
}

@Composable
private fun DropdownIndex(
    title: String,
    items: List<String>,
    selectedIndex: MutableState<Int>,
) {
    Dropdown(
        title = title,
        items = items,
        selected = items.getOrNull(selectedIndex.value),
        onSelectionChange = { index, _ -> selectedIndex.value = index },
    )
}

@Composable
private fun <Item> Dropdown(
    title: String,
    items: List<Item>,
    selected: Item?,
    onSelectionChange: (Int, Item) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(
            onClick = { expanded = true }
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("$title: ${selected ?: "Select"}")
                Icon(Icons.Default.ArrowDropDown, null)
            }

        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(item.toString()) },
                    onClick = {
                        expanded = false
                        onSelectionChange(index, item)
                    },
                )
            }
        }
    }
}