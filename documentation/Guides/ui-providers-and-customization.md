# UI Providers and Customization

ComposeTable Core owns table behavior and geometry; a `TableUI.Provider` supplies
the controls and visual defaults. Core uses Compose Foundation instead of
depending on Material 3 so that applications and libraries can render tables
with different design systems, including those supported by
[ComposeStyledComponents](https://github.com/MFlisar/ComposeStyledComponents).
See the [Core module](../Modules/core.md) for more about this separation.

`Material3TableUIProvider` is the built-in provider. Install it, or another
implementation, with `TableUI.set(...)` before rendering a table. Core has no
default renderer, so this setup is required; because the setting is global, one
provider is active for all tables in the process.

## Customize a table

`Table` accepts `shape` and `TableColors` as parameters. Use `TableDefaults`
for the active provider's default shape and colors, or pass explicit colors for
the container, content, header, selected rows, and dividers.

The `header`, `footer`, `emptyContent`, and `errorContent` slots replace or
extend the corresponding table areas. The default footer includes item count,
selection count when non-empty, and pagination in paged mode. Compose
`TableToolbar`, `TableSearchBar`, `TableFooter`, and their smaller controls in
custom slots when useful.

`headerCell` and `cell` let you customize rendering while preserving the table's
column layout. Call `Content()` on the context to use the configured column
renderer. Column-level `headerContent` and `cellContent` define reusable
renderers; `TableValueCell.Content()` delegates to the column factory's default
value renderer.

## Configure interactions

`TableInteraction` selects one row behavior: no click action, row click, cell
click, or row selection. `TableHeaderInteraction` independently controls
sortable header clicks: `SortOnClick` is the default, `MenuOnClick` shows sort
and filter actions, `Click` provides a custom callback, and `None` disables
the header surface's click behavior. Explicit controls in a custom header, such
as its filter button, remain usable. Hiding header filters does not remove
active filters.

Give `Table` a bounded height, such as `Modifier.weight(1f)` in a `Column` or
`Modifier.fillMaxSize()` in a full-screen container. It uses a lazy list for
rows and fills the remaining height between its header and footer.

## Localize built-in text

Pass `TableTexts.English`, `TableTexts.German`, or a customized `TableTexts`
to `Table`. This localizes built-in controls and accessibility descriptions;
column titles and choice-option labels remain part of the column definitions.
Override `errorMessage` to control how provider exceptions are displayed.

## Implement another UI provider

Implement `TableUI.Provider` to map the table UI primitives to another Compose
design system. The provider supplies colors, shape, typography, controls,
dividers, progress indicators, and the lazy-column host. Install the
implementation with `TableUI.set(...)` before composing `Table`; Core does not
provide a fallback renderer.
