package com.michaelflisar.composetable

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
import androidx.compose.ui.unit.dp

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
        fun HorizontalDivider(modifier: Modifier = Modifier, color: Color = Color.Unspecified)

        @Composable
        fun VerticalDivider(modifier: Modifier = Modifier, color: Color = Color.Unspecified)

        @Composable
        fun CircularProgressIndicator(modifier: Modifier = Modifier)

        @Composable
        fun LinearProgressIndicator(modifier: Modifier = Modifier)

        @Composable
        fun LazyColumn(
            state: LazyListState,
            modifier: Modifier = Modifier,
            paddingForScrollbar: Dp = 0.dp,
            itemSpacing: Dp = 0.dp,
            horizontalAlignment: Alignment.Horizontal = Alignment.Start,
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
            modifier: Modifier = Modifier,
            color: Color = Color.Unspecified,
            style: TextStyle? = null,
            maxLines: Int = Int.MAX_VALUE,
        )

        @Composable
        fun Icon(
            icon: TableIcon,
            contentDescription: String?,
            modifier: Modifier = Modifier,
            tint: Color? = null,
        )

        @Composable
        fun TextButton(
            onClick: () -> Unit,
            enabled: Boolean = true,
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
            modifier: Modifier = Modifier,
            enabled: Boolean = true,
            content: @Composable () -> Unit,
        )

        @Composable
        fun OutlinedTextField(
            value: String,
            onValueChange: (String) -> Unit,
            modifier: Modifier = Modifier,
            label: (@Composable () -> Unit)? = null,
            leadingIcon: (@Composable () -> Unit)? = null,
            singleLine: Boolean = false,
            isError: Boolean = false,
            supportingText: (@Composable () -> Unit)? = null,
        )

        @Composable
        fun InputChip(
            selected: Boolean,
            onClick: () -> Unit,
            label: @Composable () -> Unit,
            trailingIcon: (@Composable () -> Unit)? = null,
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
            leadingIcon: (@Composable () -> Unit)? = null,
            trailingIcon: (@Composable () -> Unit)? = null,
        )

        @Composable
        fun AlertDialog(
            onDismissRequest: () -> Unit,
            title: (@Composable () -> Unit)? = null,
            text: (@Composable () -> Unit)? = null,
            confirmButton: @Composable () -> Unit,
            dismissButton: (@Composable () -> Unit)? = null,
        )

        @Composable
        fun Checkbox(checked: Boolean, onCheckedChange: ((Boolean) -> Unit)?)

        @Composable
        fun RadioButton(selected: Boolean, onClick: (() -> Unit)?)
    }

    private var implementation: Provider? = null

    fun set(provider: Provider) {
        implementation = provider
    }

    val provider: Provider
        get() = implementation
            ?: error("TableUI provider is not configured. Call TableUI.set(...) before rendering table UI.")

}