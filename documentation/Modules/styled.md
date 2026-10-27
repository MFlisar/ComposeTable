# Styled Module

The `styled` module connects ComposeTable to
[ComposeStyledComponents](https://github.com/MFlisar/ComposeStyledComponents)
and provides a `TableUI.Provider` implementation backed by its styled
components. Choose it when you want to render ComposeTable using one of the
design styles supported by ComposeStyledComponents, such as Material 3, Fluent
2, or Cupertino.

Add the `core` and `styled` artifacts, configure the desired
ComposeStyledComponents renderer for the application, then install the styled
table provider with `TableUI.set(...)` before rendering tables. The provider
uses the configured styled components to render the table controls and visuals.

Use one UI implementation per application: choose `styled` for any of the
ComposeStyledComponents styles, or choose [`material3`](material3.md) for the
standalone Material 3 provider. Core does not include a default renderer, so the
selected provider must be installed explicitly; the setting is global and
applies to all tables in the process.

See [Core](core.md) for the module separation and
[UI Providers and Customization](../Guides/ui-providers-and-customization.md)
for the provider contract.
