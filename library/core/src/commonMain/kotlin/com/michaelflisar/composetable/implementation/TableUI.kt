package com.michaelflisar.composetable.implementation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.michaelflisar.composetable.TableColors

enum class TableIcon {
    Search,
    Clear,
    ArrowUpward,
    ArrowDownward,
    FilterAlt,
    Check,
    SelectAll,
    Deselect,
    FirstPage,
    ChevronLeft,
    ChevronRight,
    LastPage,
    Refresh,
}

enum class TableTextStyle {
    LabelSmall,
    TitleMedium,
}

/**
 * Keep interface methods abstract; default implementations on this interface
 * cause Wasm compilation failures. Defaults are provided as extensions in
 * TableUIProviderDefaults.kt.
 */

object TableUI {

    interface Provider {

        @Composable
        fun tableColors(): TableColors

        @Composable
        fun tableShape(): Shape

        @Composable
        fun scrollbarWidth(): Dp

        @Composable
        fun primaryColor(): Color

        @Composable
        fun errorColor(): Color

        @Composable
        fun textStyle(style: TableTextStyle): TextStyle

        @Composable
        fun buttonIconSize(): Dp

        @Composable
        fun buttonIconSpacing(): Dp

        @Composable
        fun contentColor(): Color

        @Composable
        fun ProvideContentColor(color: Color, content: @Composable () -> Unit)

        @Composable
        fun HorizontalDivider(modifier: Modifier, color: Color)

        @Composable
        fun VerticalDivider(modifier: Modifier, color: Color)

        @Composable
        fun CircularProgressIndicator(modifier: Modifier)

        @Composable
        fun LinearProgressIndicator(modifier: Modifier)

        @Composable
        fun LazyColumn(
            state: LazyListState,
            modifier: Modifier,
            paddingForScrollbar: Dp,
            itemSpacing: Dp,
            horizontalAlignment: Alignment.Horizontal,
            content: LazyListScope.() -> Unit,
        )

        @Composable
        fun Surface(
            color: Color,
            contentColor: Color,
            content: @Composable () -> Unit,
        )

        @Composable
        fun Text(
            text: String,
            modifier: Modifier,
            color: Color,
            style: TextStyle?,
            maxLines: Int,
        )

        @Composable
        fun Icon(
            icon: TableIcon,
            contentDescription: String?,
            modifier: Modifier,
            tint: Color?,
        )

        @Composable
        fun TextButton(
            onClick: () -> Unit,
            enabled: Boolean,
            content: @Composable RowScope.() -> Unit,
        )

        @Composable
        fun OutlinedButton(
            onClick: () -> Unit,
            content: @Composable RowScope.() -> Unit,
        )

        @Composable
        fun IconButton(
            onClick: () -> Unit,
            modifier: Modifier,
            enabled: Boolean,
            content: @Composable () -> Unit,
        )

        @Composable
        fun OutlinedTextField(
            value: String,
            onValueChange: (String) -> Unit,
            modifier: Modifier,
            label: (@Composable () -> Unit)?,
            leadingIcon: (@Composable () -> Unit)?,
            singleLine: Boolean,
            isError: Boolean,
            supportingText: (@Composable () -> Unit)?,
        )

        @Composable
        fun InputChip(
            selected: Boolean,
            onClick: () -> Unit,
            label: @Composable () -> Unit,
            trailingIcon: (@Composable () -> Unit)?,
        )

        @Composable
        fun DropdownMenu(
            expanded: Boolean,
            onDismissRequest: () -> Unit,
            content: @Composable ColumnScope.() -> Unit,
        )

        @Composable
        fun DropdownMenuItem(
            text: @Composable () -> Unit,
            onClick: () -> Unit,
            leadingIcon: (@Composable () -> Unit)?,
            trailingIcon: (@Composable () -> Unit)?,
        )

        @Composable
        fun AlertDialog(
            onDismissRequest: () -> Unit,
            title: (@Composable () -> Unit)?,
            text: (@Composable () -> Unit)?,
            confirmButton: @Composable () -> Unit,
            dismissButton: (@Composable () -> Unit)?,
        )

        @Composable
        fun Checkbox(checked: Boolean, onCheckedChange: ((Boolean) -> Unit)?)

        @Composable
        fun RadioButton(selected: Boolean, onClick: (() -> Unit)?)
    }

    internal var provider: Provider = TableUIProvider.impl

}