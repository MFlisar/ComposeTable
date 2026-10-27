Use the provider overload of `rememberTable` when the complete result set
should not be held in memory. The table requests a count and loads ranges as
rows become visible, while retaining a configurable cache.

## Implement the provider contract

Implement `TableDataProvider<Item>` for the source:

```kotlin
interface TableDataProvider<Item> {
    suspend fun count(query: TableQuery): Long
    suspend fun load(query: TableQuery, offset: Long, limit: Int): List<Item>
    suspend fun insert(item: Item)
    suspend fun update(key: Any, item: Item)
    suspend fun remove(key: Any)
    suspend fun replaceAll(items: List<Item>)
}
```

The provider owns global query behavior. Both `count(query)` and
`load(query, offset, limit)` must describe the same filtered and sorted result,
with deterministic ordering for equal sort values. `load` receives an absolute
offset and a maximum number of items. Return non-null rows with unique,
stable item keys; the supported count range is `0..Int.MAX_VALUE`.

`count` and `load` are separate calls, not a transactional snapshot. If the
source changes outside the model, call `table.refresh()` to invalidate cached
ranges and recount. Provider operations must cooperate with coroutine
cancellation. Mutation methods must persist to the backing source before
returning and throw when they fail; editing only an in-memory cache is not
sufficient.

## Create and configure a model

```kotlin
val table = rememberTable(
    provider = peopleProvider,
    columns = columns,
    state = rememberTableState(TableDisplay.Paged()),
    lazyConfig = TableLazyConfig(
        cache = TableCache.Lru(capacity = 8),
        prefetchPages = 1,
    ),
    itemKey = { it.id },
)
```

In continuous mode, `blockSize` and `prefetchBlocks` control range size and
prefetching. In paged mode, loads use the page size and `prefetchPages`.
`TableCache.Lru` bounds cached blocks or pages while protecting visible ones;
`TableCache.KeepAll` disables eviction. Changing the query invalidates cached
ranges and starts a new count.

The model exposes cached rows through `loadedItems`; an unloaded row is not
available through `itemAt`. Use `state.selectedKeys` for the complete selection
because `loadedSelectedItems` includes only cached rows.

## Handle loading failures and mutations

`Table` displays a progress indicator while the count or visible ranges are
loading. Its `errorContent` slot receives both count errors and per-range
errors as `TableError`; call `error.retry()` to retry the affected operation.
You can also call `table.retry()` to retry provider loading, or `table.refresh()`
to discard the cache and recount.

The model's suspend `insert`, `update`, `remove`, `replaceAll`, and `clear`
methods delegate mutations to the provider. They refresh query results after
the source accepts the change. Updating an item must preserve its key.
