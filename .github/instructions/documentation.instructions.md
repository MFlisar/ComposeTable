---
applyTo: "documentation/**/*.md,documentation/**/*.partial"
---

# Documentation authoring

Write developer documentation for ComposeTable, a Kotlin Multiplatform data-table library for Compose. Base every API claim and example on the current code in `library/core` and `library/implementations`; do not reuse component, theme, or registry documentation from other libraries.

Every Markdown page in `documentation/` must begin with a descriptive first-level heading (`# Page title`). The heading is the page's human-readable title in generated documentation; filenames are not used as display names. Use simple lowercase filenames, preferably kebab-case, such as `material3.md` or `provider-backed-data.md`.

Organize pages by the concepts readers need. `Modules/` and `Guides/` are examples, not mandatory names or the only possible folders. Module overviews are often useful; add other folders only when they help readers find related material.

Keep the two files in `documentation/_partials/`:

- `info.md.partial` is a short introduction to ComposeTable.
- `usage.md.partial` shows the essential setup and first table.

Do not place screenshots or other images in partials. The image configured by `documentation.main-screenshot-path` in `.config.yml` is inserted into the generated README automatically; do not also place that image in a documentation page. If a separate `documentation/screenshots/` folder is used, place each image in the relevant documentation page, with useful alt text and a relative link.

The root `README.md` is generated from `.config.yml`, documentation pages, and the partials. Edit those sources, not the generated README. Keep documentation focused on what a developer needs to build, configure, and extend a table:

- Explain the practical path early: add `core` and a UI provider, define columns, create a model, and render `Table`.
- Distinguish local data from `TableDataProvider` data, and describe provider query and persistence responsibilities accurately.
- Explain important behavior and constraints, including stable item and column keys, bounded table height, query semantics, paging, loading, and error handling where relevant.
- Prefer short compilable Kotlin examples whose APIs match current source. Use `begin-snippet` / `end-snippet` markers in source and `snippet: Name` in Markdown when a source-backed example is useful; do not maintain duplicate copies of that example.
- Format function, type, property, and constructor references as code, for example `rememberTable(...)` and `TableDisplay.Paged(...)`.
- Link related pages instead of repeating long explanations. Use headings when they improve scanning; do not add empty boilerplate sections.
- Use admonitions only for important prerequisites, caveats, or behavior that is easy to miss, and only with syntax supported by the renderer.

After documentation or snippet changes, run `.\gradlew.bat updateMarkdownFiles` from the repository root. The CI workflow also expands MarkdownSnippets and checks links; keep generated output in sync and avoid including unrelated working-tree changes.
