package com.michaelflisar.composetable.implementation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TableUI.Provider.HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) = HorizontalDivider(modifier, color)

@Composable
fun TableUI.Provider.VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) = VerticalDivider(modifier, color)

@Composable
fun TableUI.Provider.CircularProgressIndicator(modifier: Modifier = Modifier) =
    CircularProgressIndicator(modifier)

@Composable
fun TableUI.Provider.LinearProgressIndicator(modifier: Modifier = Modifier) =
    LinearProgressIndicator(modifier)

@Composable
fun TableUI.Provider.LazyColumn(
    state: LazyListState,
    modifier: Modifier = Modifier,
    paddingForScrollbar: Dp = 0.dp,
    itemSpacing: Dp = 0.dp,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit,
) = LazyColumn(state, modifier, paddingForScrollbar, itemSpacing, horizontalAlignment, content)

@Composable
fun TableUI.Provider.Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle? = null,
    maxLines: Int = Int.MAX_VALUE,
) = Text(text, modifier, color, style, maxLines)

@Composable
fun TableUI.Provider.Icon(
    icon: TableIcon,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color? = null,
) = Icon(icon, contentDescription, modifier, tint)

@Composable
fun TableUI.Provider.TextButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) = TextButton(onClick, enabled, content)

@Composable
fun TableUI.Provider.IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) = IconButton(onClick, modifier, enabled, content)

@Composable
fun TableUI.Provider.OutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    singleLine: Boolean = false,
    isError: Boolean = false,
    supportingText: (@Composable () -> Unit)? = null,
) = OutlinedTextField(
    value,
    onValueChange,
    modifier,
    label,
    leadingIcon,
    singleLine,
    isError,
    supportingText,
)

@Composable
fun TableUI.Provider.InputChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null,
) = InputChip(selected, onClick, label, trailingIcon)

@Composable
fun TableUI.Provider.DropdownMenuItem(
    text: @Composable () -> Unit,
    onClick: () -> Unit,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) = DropdownMenuItem(text, onClick, leadingIcon, trailingIcon)

@Composable
fun TableUI.Provider.AlertDialog(
    onDismissRequest: () -> Unit,
    title: (@Composable () -> Unit)? = null,
    text: (@Composable () -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: (@Composable () -> Unit)? = null,
) = AlertDialog(onDismissRequest, title, text, confirmButton, dismissButton)
