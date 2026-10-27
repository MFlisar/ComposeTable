package com.michaelflisar.composetable.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn as FoundationLazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Deselect
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.AlertDialog as MaterialAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox as MaterialCheckbox
import androidx.compose.material3.CircularProgressIndicator as MaterialCircularProgressIndicator
import androidx.compose.material3.DropdownMenu as MaterialDropdownMenu
import androidx.compose.material3.DropdownMenuItem as MaterialDropdownMenuItem
import androidx.compose.material3.HorizontalDivider as MaterialHorizontalDivider
import androidx.compose.material3.Icon as MaterialIcon
import androidx.compose.material3.IconButton as MaterialIconButton
import androidx.compose.material3.InputChip as MaterialInputChip
import androidx.compose.material3.LinearProgressIndicator as MaterialLinearProgressIndicator
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton as MaterialOutlinedButton
import androidx.compose.material3.OutlinedTextField as MaterialOutlinedTextField
import androidx.compose.material3.RadioButton as MaterialRadioButton
import androidx.compose.material3.Surface as MaterialSurface
import androidx.compose.material3.Text as MaterialText
import androidx.compose.material3.TextButton as MaterialTextButton
import androidx.compose.material3.VerticalDivider as MaterialVerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michaelflisar.composetable.TableColors
import com.michaelflisar.composetable.TableIcon
import com.michaelflisar.composetable.TableTextStyle
import com.michaelflisar.composetable.TableUI

/**
 * Install with [TableUI.set] before rendering table components.
 *
 *
 * */
object Material3TableUIProvider : TableUI.Provider {

    @Composable
    override fun tableColors() = TableColors(
        container = MaterialTheme.colorScheme.surface,
        content = MaterialTheme.colorScheme.onSurface,
        header = MaterialTheme.colorScheme.surfaceContainerHigh,
        headerContent = MaterialTheme.colorScheme.onSurface,
        selected = MaterialTheme.colorScheme.secondaryContainer,
        selectedContent = MaterialTheme.colorScheme.onSecondaryContainer,
        divider = MaterialTheme.colorScheme.outlineVariant,
    )

    @Composable
    override fun tableShape() = MaterialTheme.shapes.medium

    @Composable
    override fun scrollbarWidth() = 16.dp

    @Composable
    override fun primaryColor() = MaterialTheme.colorScheme.primary

    @Composable
    override fun errorColor() = MaterialTheme.colorScheme.error

    @Composable
    override fun textStyle(style: TableTextStyle): TextStyle = when (style) {
        TableTextStyle.LabelSmall -> MaterialTheme.typography.labelSmall
        TableTextStyle.TitleMedium -> MaterialTheme.typography.titleMedium
    }

    @Composable
    override fun buttonIconSize(): Dp = ButtonDefaults.IconSize

    @Composable
    override fun buttonIconSpacing(): Dp = ButtonDefaults.IconSpacing

    @Composable
    override fun contentColor() = LocalContentColor.current

    @Composable
    override fun ProvideContentColor(color: Color, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalContentColor provides color, content = content)
    }

    @Composable
    override fun HorizontalDivider(modifier: Modifier, color: Color) {
        if (color == Color.Unspecified) {
            MaterialHorizontalDivider(modifier = modifier)
        } else {
            MaterialHorizontalDivider(modifier = modifier, color = color)
        }
    }

    @Composable
    override fun VerticalDivider(modifier: Modifier, color: Color) {
        if (color == Color.Unspecified) {
            MaterialVerticalDivider(modifier = modifier)
        } else {
            MaterialVerticalDivider(modifier = modifier, color = color)
        }
    }

    @Composable
    override fun CircularProgressIndicator(modifier: Modifier) {
        MaterialCircularProgressIndicator(modifier = modifier)
    }

    @Composable
    override fun LinearProgressIndicator(modifier: Modifier) {
        MaterialLinearProgressIndicator(modifier = modifier)
    }

    @Composable
    override fun LazyColumn(
        state: LazyListState,
        modifier: Modifier,
        paddingForScrollbar: Dp,
        itemSpacing: Dp,
        horizontalAlignment: Alignment.Horizontal,
        content: LazyListScope.() -> Unit,
    ) {
        FoundationLazyColumn(
            modifier = modifier.fillMaxWidth().padding(end = paddingForScrollbar),
            verticalArrangement = Arrangement.spacedBy(itemSpacing),
            horizontalAlignment = horizontalAlignment,
            state = state,
            content = content,
        )
    }

    @Composable
    override fun Surface(color: Color, contentColor: Color, content: @Composable () -> Unit) {
        MaterialSurface(color = color, contentColor = contentColor, content = content)
    }

    @Composable
    override fun Text(
        text: String,
        modifier: Modifier,
        color: Color,
        style: TextStyle?,
        maxLines: Int,
    ) {
        if (style == null) {
            MaterialText(text = text, modifier = modifier, color = color, maxLines = maxLines)
        } else {
            MaterialText(
                text = text,
                modifier = modifier,
                color = color,
                style = style,
                maxLines = maxLines,
            )
        }
    }

    @Composable
    override fun Icon(icon: TableIcon, contentDescription: String?, modifier: Modifier, tint: Color?) {
        if (tint == null) {
            MaterialIcon(
                imageVector = icon.imageVector,
                contentDescription = contentDescription,
                modifier = modifier,
            )
        } else {
            MaterialIcon(
                imageVector = icon.imageVector,
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint,
            )
        }
    }

    @Composable
    override fun TextButton(
        onClick: () -> Unit,
        enabled: Boolean,
        content: @Composable RowScope.() -> Unit,
    ) {
        MaterialTextButton(onClick = onClick, enabled = enabled, content = content)
    }

    @Composable
    override fun OutlinedButton(onClick: () -> Unit, content: @Composable RowScope.() -> Unit) {
        MaterialOutlinedButton(onClick = onClick, content = content)
    }

    @Composable
    override fun IconButton(
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        content: @Composable () -> Unit,
    ) {
        MaterialIconButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            content = content,
        )
    }

    @Composable
    override fun OutlinedTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier,
        label: (@Composable () -> Unit)?,
        leadingIcon: (@Composable () -> Unit)?,
        singleLine: Boolean,
        isError: Boolean,
        supportingText: (@Composable () -> Unit)?,
    ) {
        MaterialOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            label = label,
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            isError = isError,
            supportingText = supportingText,
        )
    }

    @Composable
    override fun InputChip(
        selected: Boolean,
        onClick: () -> Unit,
        label: @Composable () -> Unit,
        trailingIcon: (@Composable () -> Unit)?,
    ) {
        MaterialInputChip(
            selected = selected,
            onClick = onClick,
            label = label,
            trailingIcon = trailingIcon,
        )
    }

    @Composable
    override fun DropdownMenu(
        expanded: Boolean,
        onDismissRequest: () -> Unit,
        content: @Composable ColumnScope.() -> Unit,
    ) {
        MaterialDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest,
            content = content,
        )
    }

    @Composable
    override fun DropdownMenuItem(
        text: @Composable () -> Unit,
        onClick: () -> Unit,
        leadingIcon: (@Composable () -> Unit)?,
        trailingIcon: (@Composable () -> Unit)?,
    ) {
        MaterialDropdownMenuItem(
            text = text,
            onClick = onClick,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }

    @Composable
    override fun AlertDialog(
        onDismissRequest: () -> Unit,
        title: (@Composable () -> Unit)?,
        text: (@Composable () -> Unit)?,
        confirmButton: @Composable () -> Unit,
        dismissButton: (@Composable () -> Unit)?,
    ) {
        MaterialAlertDialog(
            onDismissRequest = onDismissRequest,
            confirmButton = confirmButton,
            title = title,
            text = text,
            dismissButton = dismissButton,
        )
    }

    @Composable
    override fun Checkbox(checked: Boolean, onCheckedChange: ((Boolean) -> Unit)?) {
        MaterialCheckbox(checked = checked, onCheckedChange = onCheckedChange)
    }

    @Composable
    override fun RadioButton(selected: Boolean, onClick: (() -> Unit)?) {
        MaterialRadioButton(selected = selected, onClick = onClick)
    }
}

private val TableIcon.imageVector: ImageVector
    get() = when (this) {
        TableIcon.Search -> Icons.Default.Search
        TableIcon.Clear -> Icons.Default.Clear
        TableIcon.ArrowUpward -> Icons.Default.ArrowUpward
        TableIcon.ArrowDownward -> Icons.Default.ArrowDownward
        TableIcon.FilterAlt -> Icons.Default.FilterAlt
        TableIcon.Check -> Icons.Default.Check
        TableIcon.SelectAll -> Icons.Default.SelectAll
        TableIcon.Deselect -> Icons.Default.Deselect
        TableIcon.FirstPage -> Icons.Default.FirstPage
        TableIcon.ChevronLeft -> Icons.Default.ChevronLeft
        TableIcon.ChevronRight -> Icons.Default.ChevronRight
        TableIcon.LastPage -> Icons.Default.LastPage
        TableIcon.Refresh -> Icons.Default.Refresh
    }
