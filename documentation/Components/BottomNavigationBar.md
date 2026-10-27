`StyledBottomNavigationBar` replaces the former toolbar-style `StyledBottomBar`.
  Use `RowScope.StyledBottomNavigationBarItem` for equally weighted destinations
  with `selected`, `onClick`, icon, optional label, `enabled` and `alwaysShowLabel`.
  The caller owns the selected destination and navigation. Items support selected
  and unselected content colors, indicator color and an interaction source.
  Supply an accessible icon description when omitting a label.
  Theme factories expose `bottomNavigationBar` instead of `bottomBar`; Scaffold's
  generic `bottomBar` slot keeps its name.

Material3 uses Compose Material3 `NavigationBar` and `NavigationBarItem` for bottom
navigation, not `BottomAppBar`. Cupertino uses tint-based tab selection without
a filled indicator; Fluent2 uses a filled selected-icon indicator. Both support
disabled destinations and keyboard focus.

Bottom bars handle their safe-area insets themselves. Pass
`WindowInsets(0, 0, 0, 0)` when embedding a bar inside other content.
