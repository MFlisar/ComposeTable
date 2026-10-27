[![MFlisar/maven-repository](https://img.shields.io/badge/MFlisar-maven--repository-brightgreen?color=blue)](https://MFlisar.github.io/maven-repository) ![API](https://img.shields.io/badge/api-24%2B-brightgreen.svg?style=for-the-badge) ![Kotlin](https://img.shields.io/github/languages/top/MFlisar/ComposeTable.svg?color=blueviolet) ![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin_Multiplatform-blue?label=Kotlin)
# ComposeTable
![Platforms](https://img.shields.io/badge/PLATFORMS-black?style=for-the-badge) ![Android](https://img.shields.io/badge/android-3DDC84?style=for-the-badge) ![iOS](https://img.shields.io/badge/ios-A2AAAD?style=for-the-badge) ![Windows](https://img.shields.io/badge/windows-5382A1?style=for-the-badge) ![macOS](https://img.shields.io/badge/macos-B0B0B0?style=for-the-badge) ![WebAssembly](https://img.shields.io/badge/wasm-624DE7?style=for-the-badge)

ComposeStyledComponents is a Kotlin Multiplatform library for Compose. It provides
themed UI components through a consistent API, making it easy to build
applications with different design systems.

**Main features**
- `Styled*` components for common controls, navigation, and layouts
- Ready-to-use themes and renderers for Material 3, Cupertino, and Fluent 2
- Shared design tokens for colors, spacing, and other theme values
- Replaceable renderers through a central component registry
- Customizable themes and targeted overrides for individual components
- Foundation-based renderers as a starting point for custom implementations
- Support for Android, iOS, Windows, macOS, and WebAssembly

[![Open Interactive Demo](https://img.shields.io/badge/Compose_Table_Demo-2962FF?style=for-the-badge&logo=googlechrome&logoColor=white)](https://mflisar.github.io/ComposeTable/demo/)
[![Open Interactive Demo](https://img.shields.io/badge/Invalid_Demo-2962FF?style=for-the-badge&logo=googlechrome&logoColor=white)](https://mflisar.github.io/ComposeTable/demo/)

# Table of Contents

- [Supported Platforms](#computer-supported-platforms)
- [Versions](#arrow_right-versions)
- [Setup](#wrench-setup)
- [Usage](#rocket-usage)
- [Demo](#sparkles-demo)
- [API](#books-api)
- [Other Libraries](#bulb-other-libraries)

# :computer: Supported Platforms

| Module | android | iOS | windows | macOS | wasm | Notes |
|---|---|---|---|---|---|---|
| core | ✅ | ✅ | ✅ | ✅ | ✅ | the core module |
| material3 | ✅ | ✅ | ✅ | ✅ | ✅ | a material3 implementation |
| styled | ✅ | ✅ | ✅ | ✅ | ✅ | a ComposeStyled implementation |

# :arrow_right: Versions

| Dependency | Version |
|---|---|
| Kotlin | `2.4.21` |
| Jetbrains Compose | `1.12.2` |
| Jetbrains Compose Material3 | `1.9.0` |

# :wrench: Setup

<b>This library is not published on maven central</b>

Instead it's published on GitHub, so you need to add the following repository to your settings.gradle.kts:

```java
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        // ADD THIS
        maven("https://MFlisar.github.io/maven-repository/artifacts")
        // ...
    }
}
```

<details open>

<summary><b>Using Version Catalogs</b></summary>

<br>

Define the dependencies inside your **libs.versions.toml** file.

```toml
[versions]

composetable = "<LATEST-VERSION>"

[libraries]

composetable-core = { module = "io.github.mflisar.composetable:core", version.ref = "composetable" }
composetable-material3 = { module = "io.github.mflisar.composetable:material3", version.ref = "composetable" }
composetable-styled = { module = "io.github.mflisar.composetable:styled", version.ref = "composetable" }
```

And then use the definitions in your projects **build.gradle.kts** file like following:

```java
implementation(libs.composetable.core)
implementation(libs.composetable.material3)
implementation(libs.composetable.styled)
```

</details>

<details>

<summary><b>Direct Dependency Notation</b></summary>

<br>

Simply add the dependencies inside your **build.gradle.kts** file.

```kotlin
val composetable = "<LATEST-VERSION>"

implementation("io.github.mflisar.composetable:core:${composetable}")
implementation("io.github.mflisar.composetable:material3:${composetable}")
implementation("io.github.mflisar.composetable:styled:${composetable}")
```

</details>

# :rocket: Usage

#### Basic usage

Add the `core` module and the implementation module for the theme you want to use.
Wrap your UI in `StyledTheme`, then use the shared `Styled*` components. The active
theme supplies their appearance and behavior.

```kotlin
StyledTheme(
    platform = platform,
    theme = rememberMaterial3Theme(),
) {
    StyledButton(onClick = { /* Handle the action */ }) {
        StyledText("Continue")
    }
}
```

Browse the [component guides](documentation/Components/), [module overviews](documentation/Modules/)
and [advanced guides](documentation/Advanced/) for details.

Browse following guides for more details.

- Modules
  - [Core](documentation/Modules/Core.md)
  - [Cupertino](documentation/Modules/Cupertino.md)
  - [Fluent2](documentation/Modules/Fluent2.md)
  - [Foundation](documentation/Modules/Foundation.md)
  - [Material3](documentation/Modules/Material3.md)
- Advanced
  - [ThemeSwitching](documentation/Advanced/ThemeSwitching.md)
  - [ThemeTokens](documentation/Advanced/ThemeTokens.md)
  - [ThemesAndRegistry](documentation/Advanced/ThemesAndRegistry.md)
- Components
  - [BottomNavigationBar](documentation/Components/BottomNavigationBar.md)
  - [Button](documentation/Components/Button.md)
  - [Card](documentation/Components/Card.md)
  - [Checkbox](documentation/Components/Checkbox.md)
  - [Chip](documentation/Components/Chip.md)
  - [Divider](documentation/Components/Divider.md)
  - [Dropdown](documentation/Components/Dropdown.md)
  - [FloatingActionButton](documentation/Components/FloatingActionButton.md)
  - [GroupBox](documentation/Components/GroupBox.md)
  - [IconButton](documentation/Components/IconButton.md)
  - [Input](documentation/Components/Input.md)
  - [LabeledInformation](documentation/Components/LabeledInformation.md)
  - [LazyAndGridLayouts](documentation/Components/LazyAndGridLayouts.md)
  - [NavigationItems](documentation/Components/NavigationItems.md)
  - [NavigationRail](documentation/Components/NavigationRail.md)
  - [NumberPicker](documentation/Components/NumberPicker.md)
  - [Picker](documentation/Components/Picker.md)
  - [PopupMenus](documentation/Components/PopupMenus.md)
  - [ProgressIndicator](documentation/Components/ProgressIndicator.md)
  - [RadioButton](documentation/Components/RadioButton.md)
  - [Scaffold](documentation/Components/Scaffold.md)
  - [ScrollbarContainers](documentation/Components/ScrollbarContainers.md)
  - [SegmentedButton](documentation/Components/SegmentedButton.md)
  - [Snackbar](documentation/Components/Snackbar.md)
  - [Surface](documentation/Components/Surface.md)
  - [Switch](documentation/Components/Switch.md)
  - [Tabs](documentation/Components/Tabs.md)
  - [Tooltip](documentation/Components/Tooltip.md)
  - [TopBar](documentation/Components/TopBar.md)

# :sparkles: Demo

Following full demo projects are included:
- [Compose Table Demo](demo/app/compose)
- [Invalid Demo](demo/app/compose)

# :books: API

Check out the [API documentation](https://MFlisar.github.io/ComposeTable/).

# :bulb: Other Libraries

You can find more of my multiplatform libraries that work well together [here](https://mflisar.github.io/Libraries/).

# :twisted_rightwards_arrows: Compatibility

When combining my libraries, you can find compatibility information [here](https://mflisar.github.io/Libraries/compatibilities/).
