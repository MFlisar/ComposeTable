# Styled Module

The `styled` module connects ComposeTable to
[ComposeStyled](https://github.com/MFlisar/ComposeStyled).
Choose it when you want to render ComposeTable using one of the design styles
supported by ComposeStyled, such as Material 3, Fluent 2, or
Cupertino.

Add the `styled` artifact and configure the desired ComposeStyled
renderer for the application. The `styled` dependency automatically selects
the ComposeStyled-based table implementation; no separate table
renderer registration is needed. ComposeTable uses the configured styled
components to render the table controls and visuals.

Choose either this module or [`material3`](material3.md) as the ComposeTable
implementation dependency.

See [Getting Started](../Guides/getting-started.md) for a complete table
example and [Table Customization](../Guides/table-customization.md) for ways to
customize its content and interactions.
