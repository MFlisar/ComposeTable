[![Maven Central](https://img.shields.io/maven-central/v/io.github.mflisar.composetable/material3?color=blue)](https://central.sonatype.com/artifact/io.github.mflisar.composetable/material3) ![API](https://img.shields.io/badge/api-24%2B-brightgreen.svg) ![Kotlin](https://img.shields.io/github/languages/top/MFlisar/ComposeTable.svg?color=blueviolet) ![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin_Multiplatform-blue?label=Kotlin)
# ComposeTable
![Platforms](https://img.shields.io/badge/PLATFORMS-black?style=for-the-badge) ![Android](https://img.shields.io/badge/android-3DDC84?style=for-the-badge) ![iOS](https://img.shields.io/badge/ios-A2AAAD?style=for-the-badge) ![Windows](https://img.shields.io/badge/windows-5382A1?style=for-the-badge) ![macOS](https://img.shields.io/badge/macos-B0B0B0?style=for-the-badge) ![WebAssembly](https://img.shields.io/badge/wasm-624DE7?style=for-the-badge)

ComposeTable is a Kotlin Multiplatform data-table library for Compose. It provides
typed columns, local and provider-backed data, and built-in sorting, filtering,
paging, and selection, with Material 3 and ComposeStyled UI variants.

> [!TIP]
> Try the interactive demo:
> 
> [![Open Interactive Demo](https://img.shields.io/badge/Compose_Table_Demo-2962FF?style=for-the-badge&logo=googlechrome&logoColor=white)](https://mflisar.github.io/ComposeTable/demo/)

# :camera: Screenshot

![Screenshot](documentation/screenshots/main.png)

# Table of Contents

- [Supported Platforms](#computer-supported-platforms)
- [Versions](#arrow_right-versions)
- [Setup](#wrench-setup)
- [Usage](#rocket-usage)
- [Demo](#sparkles-demo)
- [API](#books-api)
- [Other Libraries](#bulb-other-libraries)

# :computer: Supported Platforms

| Module | android | ios | windows | macos | wasm | Notes |
|---|---|---|---|---|---|---|
| material3 | ✅ | ✅ | ✅ | ✅ | ✅ | Material 3 Variant |
| styled | ✅ | ✅ | ✅ | ✅ | ✅ | ComposeStyled Variant |

# :arrow_right: Versions

| Dependency | Version |
|---|---|
| Kotlin | `2.4.21` |
| Jetbrains Compose | `1.12.2` |
| Jetbrains Compose Material3 | `1.9.0` |

# :wrench: Setup

<details open>

<summary><b>Using Version Catalogs</b></summary>

<br>

Define the dependencies inside your **libs.versions.toml** file.

```toml
[versions]

composetable = "<LATEST-VERSION>"

[libraries]

composetable-material3 = { module = "io.github.mflisar.composetable:material3", version.ref = "composetable" }
composetable-styled = { module = "io.github.mflisar.composetable:styled", version.ref = "composetable" }
```

And then use the definitions in your projects **build.gradle.kts** file like following:

```java
// Choose one implementation:
implementation(libs.composetable.material3)
// implementation(libs.composetable.styled)
```

</details>

<details>

<summary><b>Direct Dependency Notation</b></summary>

<br>

Simply add the dependencies inside your **build.gradle.kts** file.

```kotlin
val composetable = "<LATEST-VERSION>"

// Choose one implementation:
implementation("io.github.mflisar.composetable:material3:${composetable}")
// implementation("io.github.mflisar.composetable:styled:${composetable}")
```

</details>

# :rocket: Usage

Add either the `material3` or `styled` implementation dependency, define typed
columns, create a model, and give `Table` a bounded height:

snippet: GettingStartedTable

`initialItems` is evaluated when the remembered model is created; use the
model's mutation methods or a new model when replacing the data. When using the
`material3` variant, compose the table under `MaterialTheme`. With the `styled`
variant, configure the desired ComposeStyledComponents renderer.

Browse the documentation for setup and details:

- General
  - [Getting Started](documentation/Guides/getting-started.md)
- Modules
  - [Material 3](documentation/Modules/material3.md)
  - [Styled](documentation/Modules/styled.md)
- Advanced
  - [Columns and Sorting](documentation/Guides/columns-and-sorting.md)
  - [Filtering and Search](documentation/Guides/filtering-and-search.md)
  - [Provider-backed Data](documentation/Guides/provider-backed-data.md)
  - [State, Paging, and Selection](documentation/Guides/state-paging-and-selection.md)
  - [Table Customization](documentation/Guides/table-customization.md)

# :sparkles: Demo

A [demo project](demo) is included.

# :books: API

Check out the [API documentation](https://MFlisar.github.io/ComposeTable/api/).

# :bulb: Other Libraries

You can find more of my multiplatform libraries that work well together [here](https://mflisar.github.io/Libraries/).

# :twisted_rightwards_arrows: Compatibility

When combining my libraries, you can find compatibility information [here](https://mflisar.github.io/Libraries/compatibilities/).
